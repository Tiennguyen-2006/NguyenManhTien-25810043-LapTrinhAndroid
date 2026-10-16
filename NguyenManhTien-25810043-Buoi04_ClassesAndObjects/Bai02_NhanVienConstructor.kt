//25810043
//Nguyen Manh Tien
package bai02

class NhanVien(maNhanVien: String, val ten: String, var luongThang: Double) {
    constructor(ten: String) : this("TAM", ten, 0.0)
}

fun main() {
    val nv1 = NhanVien("NV01", "Nguyen Van A", 1000.0)
    val nv2 = NhanVien("Tran Thi B")
    
    // nv1.maNhanVien 
    // Giải thích: Dòng trên sẽ báo lỗi biên dịch vì maNhanVien không được khai báo 
    // với từ khóa val hay var trong primary constructor, nên nó chỉ là một tham số 
    // truyền vào chứ không trở thành thuộc tính của class.

    println("Nhan vien 1: Ten = ${nv1.ten}, Luong = ${nv1.luongThang}")
    println("Nhan vien 2: Ten = ${nv2.ten}, Luong = ${nv2.luongThang}")
}
