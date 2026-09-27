//25810043
//Nguyen Manh Tien
fun main() {
    val tuoi = 25
    val loaiVe = if (tuoi < 12) {
        "Ve tre em"
    } else if (tuoi < 60) {
        "Ve nguoi lon"
    } else {
        "Ve cao tuoi"
    }
    println("Loai ve: $loaiVe")
}
main()

