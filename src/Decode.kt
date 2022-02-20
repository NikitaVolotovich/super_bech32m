class Decode {
    fun isZero(a: Int, b: Int): Boolean {
        if(a+b == 0 || a-b == 0|| a*b == 0){
            return true
        }
        return false
    }
}