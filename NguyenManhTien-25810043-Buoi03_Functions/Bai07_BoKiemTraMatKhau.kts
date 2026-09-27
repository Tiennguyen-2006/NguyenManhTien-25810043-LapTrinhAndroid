//25810043
//Nguyen Manh Tien
val kiemTraDoDai: (String) -> Boolean = { password -> password.length >= 8 }

val res1 = println(kiemTraDoDai("1234567"))
val res2 = println(kiemTraDoDai("12345678"))
val res3 = println(kiemTraDoDai("Password123!"))