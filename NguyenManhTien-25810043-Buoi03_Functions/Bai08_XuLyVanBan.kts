//25810043
//Nguyen Manh Tien
fun xuLyVanBan(vanBan: String, hamXuLy: (String) -> String): String {
    return hamXuLy(vanBan)
}
fun vietHoa(vanBan: String): String {
    return vanBan.uppercase()
}
val res1 = xuLyVanBan("hello", { str -> str.lowercase() })
val res2 = xuLyVanBan("hello", ::vietHoa)
val res3 = xuLyVanBan("hello") { str ->
    str + " world"
}