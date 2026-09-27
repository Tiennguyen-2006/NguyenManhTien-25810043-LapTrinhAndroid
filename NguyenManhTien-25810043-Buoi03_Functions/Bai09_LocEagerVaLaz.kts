//25810043
//Nguyen Manh Tien
val nhacCu = listOf("Guitar", "Piano", "Gong", "Violin", "Guzheng")
val res1 = nhacCu.filter { it.startsWith("G") }
val res2 = nhacCu.asSequence().filter { it.startsWith("G") }.toList()
println(res1)
println(res2)
