//25810043
//Nguyen Manh Tien
package bai06

open class DongVat(val ten: String) {
    open fun keu(): String {
        return "Tiếng kêu mặc định"
    }
}

class Cho(ten: String) : DongVat(ten) {
    override fun keu(): String {
        return "Gâu gâu"
    }
}

class Meo(ten: String) : DongVat(ten) {
    override fun keu(): String {
        return "Meo meo"
    }
}

fun main() {
    val danhSachDongVat = listOf(
        Cho("Cún"),
        Meo("Mimi"),
        Cho("Vàng"),
        DongVat("Động vật lạ")
    )

    for (dv in danhSachDongVat) {
        println("Tên: ${dv.ten} - Kêu: ${dv.keu()}")
    }
}
