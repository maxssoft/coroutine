package com.example.kotlindemo

import android.os.Handler
import android.os.Looper
import android.util.Log
import kotlinx.coroutines.*
import rx.Single
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers
import java.lang.RuntimeException
import java.lang.StringBuilder

/**
 * @author Сидоров Максим on 2019-12-23.
*/

/**
 * Тестовый интерактор, эмулирующий фоновые задачи
 */
class Interactor {

    private lateinit var logger: Logger

    fun initLogger(logger: Logger) {
        this.logger = logger
    }

    suspend fun doTask1(): String {
        logger.log("start doTask1()")
        delay(1000L)
        logger.log("finish doTask1()")

        return "task1 completed"
    }

    suspend fun doTask2(): String {
        logger.log("start doTask2()")
        delay(750L)
        logger.log("finish doTask2()")
        return "task2 completed"
    }

    suspend fun doLongTask(): String {
        logger.log("start doLongTask()")
        for (i in 1..30) {
            logger.log("doLongTask() step $i")
            //delay(1000L)
            Thread.sleep(1000L)
            yield() // позволяет системе прерывать функцию
        }
        logger.log("finish doLongTask()")
        return "doLongTask completed"
    }

    suspend fun doTaskWithException(): String {
        logger.log("start doTaskWithException()")
        delay(500L)
        logger.log("throw doTaskWithException()")
        throw RuntimeException("Error of method doTaskWithException")
        return "doTaskWithException completed"
    }

    fun doRxTask(): Single<String> {
        return Single.fromCallable {
            Thread.sleep(100L)
            "doRxTask()"
        }
    }

    suspend fun doCoroutineTask(): String {
        Thread.sleep(100L)
        return "doCoroutineTask()"
    }
}

/**
 * Тестовый презентер
 */
class Presenter {

    /**
     * Главный ui скоуп, в рамках которого выполняются все фоновые задачи и возвращаются результаты в UI поток
     * При отвязке презентера от View нужно отменять в нем все созданные задачи -> mainScope.cancle()
     *
     * По сути является аналогом CompositeDisposable
     */
    private var mainScope = MainScope()

    /**
     * Обработчик ошибок, возникающих при выполнении корутин
     * Важно понимать, что обработчик срабатывает в рамках того потока, в котором было вызвано исключение
     * Для того чтобы обрабатывать ошибку на UI потоке, запускаем ее обработку в отдельной таске на UI потоке
     */
    private val exceptionHandler = CoroutineExceptionHandler { coroutineContext, exception ->
        mainScope.launch { handleException(exception) }
    }

    private lateinit var logger: Logger
    private val interactor = Interactor()

    private var view: TestView? = null

    /**
     * Присоединение ко view
     */
    fun attachView(view: TestView) {
        this.view = view
        initLogger()
    }

    /**
     * Отвязка презентера от View и отмена всех фоновых задачи из mainScope
     */
    fun detachView() {
        // Отменяем все фоновые задачи
        mainScope.cancel()
        view = null
        initLogger()
    }

    /**
     * Пример выполнения простого задания на фоновом потоке
     */
    fun loadSimple() {
        logger.clear()
        // Запускае корневую корутину в основном скоупе
        mainScope.launch(exceptionHandler) {
            showProgress()

            // Запускаем дочернюю корутину на фоновом потоке и получаем ее результат
            // withContext() запускает вложенную корутину на текущем контексте и всегда ждет ее завершения
            val taskResult = withContext(Dispatchers.Default) { interactor.doTask1() }

            updateText("loadSimple: " + taskResult)
            hideProgress()
        }
    }

    /**
     * Пример выполнения двух послдовательных фоновых задач и возврат общего результата
     */
    fun loadFlatMap() {
        logger.clear()

        // Запускае корневую корутину в основном скоупе
        mainScope.launch(exceptionHandler) {
            showProgress()

            // Запускаем дочернюю корутину на фоновом потоке и дожидаемся ее завершения
            // внутри корутины задачи будут выполнятся последовательно
            var completedTasks: String = withContext(Dispatchers.Default) {
                return@withContext interactor.doTask1() + ", " +
                        interactor.doTask2()
            }

            updateText("loadFlatMap: " + completedTasks)
            hideProgress()
        }
    }


    /**
     * Пример выполнения двух паралельных фоновых задач и возврат общего результата
     */
    fun loadWithZip() {
        logger.clear()
        // Запускае корневую корутину в основном скоупе
        mainScope.launch(exceptionHandler) {
            showProgress()

            // запускаем 2 паралельные фоновые задачи
            // запуск через async() не будет ждать завершения задачи
            val task1 = async(Dispatchers.Default) { interactor.doTask1() }
            val task2 = async(Dispatchers.Default) { interactor.doTask2() }

            // Ждем завершения запущеннных задач и забираем из них результат
            // await() - дожидается выполнения задачи и возвращает ее результат
            val completedTasks = task1.await() + ", " + task2.await()

            // Альтернативный способ ожидания завершения задач
            // joinAll(task1, task2)

            updateText("loadWithZip: " + completedTasks)
            hideProgress()
        }
    }

    /**
     * Пример обработки исключений при выполнении фоновых задач
     */
    fun loadWithError() {
        logger.clear()
        // Стартуем фоновый процесс в основном mainScope
        mainScope.launch(exceptionHandler) {
            launch {  }
            showProgress()
            // запускаем 3 паралельные фоновые задачи
            val task1 = async(Dispatchers.Default) { interactor.doTask1() }
            val task2 = async(Dispatchers.Default) { interactor.doTask2() }
            val taskWithError =
                async(Dispatchers.Default) { interactor.doTaskWithException() }

            val completedTasks =
                task1.await() + ", " + task2.await() + ", " + taskWithError.await()

            updateText("loadWithError: " + completedTasks)
            hideProgress()
        }
    }

    /**
     * Тест на время выполнения 1000 RX вызовов
     */
    fun loadRx(stepList: StepList) {
        logger.clear()
        showProgress()
        logger.log("rx start ${stepList.count} tasks")

        for (step in 1..stepList.count) {
            interactor.doRxTask()
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                        // logger.log("rx step $step finished")
                        stepList.finishStep()
                        if (stepList.isFinished) {
                            hideProgress()
                            logger.log("rx all task finished at ${stepList.workTimeMs} ms")
                        }
                    }, this::handleException)

        }
    }

    /**
     * Тест на время выполнения 1000 паралельных корутин
     */
    fun loadCoroutine(stepList: StepList) {
        logger.clear()
        showProgress()
        logger.log("coroutine start ${stepList.count} tasks")

        for (step in 1..stepList.count) {
            mainScope.launch(exceptionHandler) {
                val result = withContext(Dispatchers.Default) {
                    interactor.doCoroutineTask()
                }
                // logger.log("coroutine step $step finished")
                stepList.finishStep()
                if (stepList.isFinished) {
                    hideProgress()
                    logger.log("coroutine all task finished at ${stepList.workTimeMs} ms")
                }
            }
        }
    }

    /**
     * Запускает длительную задачу для проверки отмены выполнения корутины
     */
    fun loadLongTask() {
        logger.clear()
        mainScope.launch(exceptionHandler) {
            showProgress()

            // Выполняем длинную вложенную фоновую задачу и получаем ее результат
            val taskResult = withContext(Dispatchers.Default) { interactor.doLongTask() }

            updateText("loadLongTask: " + taskResult)
            hideProgress()
        }
    }

    /**
     * Отображает ошибку во вью
     */
    private fun handleException(error: Throwable) {
        logger.log("error: $error")
        view?.hideProgress()
    }


    /**
     * Функции, обновляющие view
     */
    private fun updateText(text: String) {
        logger.log(text)
        view?.updateStatus(text)
    }
    private fun showProgress() {
        logger.log("showProgress()")
        view?.showProgress()
    }
    private fun hideProgress() {
        logger.log("hideProgress()")
        view?.hideProgress()
    }

    /**
     * Отменяет выполнение всех корутин скоупа и перенсоздает скоуп
     */
    @Synchronized
    fun cancel() {
        logger.log("mainScope cancelled")
        // отменяем старый скоуп
        mainScope.cancel()

        // создаем новый скоуп
        mainScope = MainScope()
        hideProgress()
    }

    private fun initLogger() {
        logger = Logger(view)
        interactor.initLogger(logger)
    }

}

/**
 * Вспомогательный класс для сравнения быстродействия RX и корутин
 */
class StepList(val count: Int) {

    private val startTime: Long
    private var finishTime: Long = 0L

    init {
        startTime = System.currentTimeMillis()
    }

    private var finishedCount: Int = 0

    val isFinished: Boolean
        get() = finishTime > 0

    val workTimeMs: Long
        get() = finishTime - startTime

    fun finishStep() {
        finishedCount++
        if (finishedCount >= count) {
            finishTime = System.currentTimeMillis()
        }
    }
}

/**
 * Вспомогательный класс для вывода логов во вью
 */
class Logger(private val view: TestView?) {

    private val log = StringBuilder()
    private val handler = Handler(Looper.getMainLooper())

    fun log(text: String) {
        Log.w(TAG, "text = $text")

        log.append(text).append("\n")
        handler.post { view?.updateLog(log.toString()) }
    }

    fun clear() {
        log.clear()
    }

    val text: String
        get() = log.toString()
}


const val TAG = "demo test"
