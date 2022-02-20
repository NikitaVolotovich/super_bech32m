
// From https://en.bitcoin.it/wiki/Bech32#:~:text=Bech32%20is%20a%20segwit%20address,is%20the%20preferred%20address%20scheme.
const val testVector1 = "4ef47f6eb681d5d9fa2f7e16336cd629303c635e8da51e425b76088be9c8744c"
const val testVector2 = "514a33f1d46179b89e1fea7bbb07b682ab14083a276979f91038369d1a8d689b"
const val testVector3 = "bc1qar0srrr7xfkvy5l643lydnw9re59gtzzwf5mdq"
const val testVector4 = "bc1qc7slrfxkknqcq2jevvvkdgvrt8080852dfjewde450xdlk4ugp7szw5tk9"
const val testVector5 = "bc1pw508d6qejxtdg4y5r3zarvary0c5xw7kw508d6qejxtdg4y5r3zarvary0c5xw7k7grplx"

fun main(){
    print("PA193 course project start.")
    test()
}

fun test(){
    println(testVector1)
    println(testVector2)
    println(testVector3)
    println(testVector4)
    println(testVector5)
}


