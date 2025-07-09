package com.menna.caffeine.ui.screen.select_size_and_beans

data class SelectSizeState(
    val selectedSize: CupSize = CupSize.M,
    val selectedBeansSize: BeansSize = BeansSize.LOW,
    val cupVolume: Int = 200,
    val cupScale: Float = 1.0f
) {
    enum class CupSize {
        S, M, L;

        internal fun getVolume(): Int = when (this) {
            S -> 150
            M -> 200
            L -> 400
        }

        internal fun getScale(): Float = when (this) {
            S -> 0.8f
            M -> 1.0f
            L -> 1.2f
        }
    }

    enum class BeansSize {
        LOW, MEDIUM, HIGH;


    }
}