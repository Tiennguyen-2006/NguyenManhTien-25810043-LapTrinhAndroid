//25810043
//Nguyen Manh Tien
val canNang: Double = 65.0
val chieuCao: Double = 1.72

fun main() {
    val bmi: Double = canNang / (chieuCao * chieuCao)
    var phanLoai: String = ""

    if (bmi < 18.5) {
        phanLoai = "Gay"
    } else if (bmi < 25.0) {
        phanLoai = "Binh thuong"
    } else if (bmi < 30.0) {
        phanLoai = "Thua can"
    } else {
        phanLoai = "Beo phi"
    }
    println("Chiso BMI: $bmi")
    println("Phan loai: $phanLoai")
}

main()