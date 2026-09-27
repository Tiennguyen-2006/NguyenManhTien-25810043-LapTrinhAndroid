//25810043
//Nguyen Manh Tien
fun main() {
    val dsSach = mutableListOf("toan", "van", "tieng anh", "tin hoc", "lich su")
    println("Danh sach ban dau: $dsSach")
    dsSach.add("dia ly")
    dsSach.add("cong nghe")
    dsSach.remove("van")
    dsSach.sort()
    println("Danh sach sau khi xu ly: $dsSach")
}

main()