package ds2.core.api

interface IdAware<T> : java.io.Serializable {
    fun getId(): T
}
