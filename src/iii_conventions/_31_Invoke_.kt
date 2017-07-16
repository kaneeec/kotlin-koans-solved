package iii_conventions

import util.TODO


class Invokable {
    var invokes = 0

    fun getNumberOfInvocations() = invokes

    operator fun invoke(): Invokable {
        invokes++
        return this
    }
}

//operator fun Any.invoke(): Any = this.invokes++

fun todoTask31(): Nothing = TODO(
        """
        Task 31.
        Change the class 'Invokable' to count the number of invocations:
        for 'invokable()()()()' it should be 4.
    """,
        references = { invokable: Invokable -> })

fun task31(invokable: Invokable): Int {
    return invokable()()()().getNumberOfInvocations()
}


