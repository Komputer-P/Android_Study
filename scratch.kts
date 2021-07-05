import android.graphics.Color
import android.view.View
import java.util.*

/*** 기본 구문 ***/
// 변수와 상수
var aa: Int = 10
val bb: Int = 20

// 함수
fun greet(str: String): Unit {
    println(str)
}

greet("hello")

/* 기본 자료형 */
// 숫자형, 리터럴
var c = 10.0f

// 문자형
val str = "hello"
val char = 'h'

str == "hello"

println("$str kevin")

// 배열
val numbers: Array<Int> = arrayOf(1,2,3,4,5);
numbers[0] = 5;


/*** 제어문 ***/
// if, while
// 자바와 동일
val max = if (aa>bb) aa else bb

// when
// switch와 동일
val x = 1

when(x) {
    1 -> println("x == 1")
    2,3 -> println("x == 2 or x == 3")
    in 4..7 -> println("4부터 7사이")
    !in 8..10 -> println("8부터 10 사이가 아님")
    else -> {
        print("x는 1이나 2가 아님")
    }
}

// for
for (num in numbers) {
    println(num)
}

for (i in 1..3) {
    println(i)
}


/*** 클래스 ***/
// 선언, 생성자
class Persons {
    constructor(name: String) {
        println(name)
    }
}

// 프로퍼티
class Person(var name: String) {

}

val person = Person("멋쟁이")
person.name = "키다리"
println(person.name)

// 접근 제한자
class A {
    var a = 1 // public: 전체 공개
    private val b = 2 // 현재 파일 내부에서만 사용 가능
    protected val c = 3 // 상속받은 클래스에서 사용 가능
    internal val d = 4 // 같은 모듈 내에서만 사용 가능
}

// 클래스의 상속
// 코틀린에서의 클래스는 기본적으로 상속이 금지됨, 가능하게 하려면 open 키워드 추가
open class Animal {

}

class Dogs : Animal() {

}

// 내부 클래스
class OuterClass {
    var a = 10

    // 내부 클래스
    inner class OuterClass2 {}
    fun something() {
        a = 20 // 접근 가능
    }
}

// 추상 클래스
abstract class C {
    abstract fun func()

    fun fun2() {

    }
}

class B: C() {
    override fun func() {
        println("hello")
    }
}

val a = B()


/*** 인터페이스 ***/
// 인터페이스의 선언
interface Runnable {
    fun run()

    fun fastRun() = println("빨리 달린다")
}

// 인터페이스의 구현
interface Eatable {
    fun eat()
}

class Dog : Animal(), Runnable, Eatable {
    override fun eat() {
        println("먹는다")
    }

    override fun run() {
        println("달린다")
    }
}

val dog = Dog()
dog.run()
dog.eat()


/*** NULL 가능성 ***/
// 기본적으로 객체를 불변으로 보고 null 값을 허용하지 않음

// null 허용?
// 따라서 모든 객체는 생성과 동시에 값을 대입하여 초기화해야 함
val a : String? = null // null 값을 허용하려면 자료형의 오른쪽에 ? 기호를 붙이면 됨

// lateinit 키워드로 늦은 초기화: var 에서 사용
lateinit var aaa : String

aaa = "hello"
println(aaa)

// lazy로 늦은 초기화: val 에서 사용
val str: String by lazy {
    println("초기화")
    "hello"
}

println(str)
println(str)

// null 값이 아님을 보증 (!!)
val name: String? = "키다리"

val name3: String? = name
val name4: String = name!!

// 안전한 호출 (?.)
// 메서드 호출 시 null 값이 아닌 경우에만 호출됨
val nname: String? = null

var upperCase = if (str != null) str else null
upperCase = str?.uppercase(Locale.getDefault())

// 엘비스 연산자 (?:)
// 안전한 호출 시 null이 아닌 기본값을 반환하고 싶을 때 사용
val str: String? = null

upperCase = str?.uppercase(Locale.getDefault()) ?: "초기화하시오"


/*** 컬렉선 ***/
/* 컬렉션: 개발에 유용한 자료구조 */

// 리스트: 배열의 구조를 가진, 중복된 아이템을 가질 수 있는 자료구조
val foodss: List<String> = listOf("라면", "갈비", "밥") // 읽기 전용

val foods = mutableListOf("라면", "갈비", "밥") // 변경 가능

foods.add("초밥")
foods.removeAt(0)
foods[1] = "부대찌개"

println(foods)

// 맵: 키와 값의 쌍으로 이루어진 키가 중복될 수 없는 자료구조
val map = mapOf("a" to 1, "b" to 2, "c" to 3) // 읽기 전용

val citiesMap = mutableMapOf("한국" to "서울",
        "일본" to "동경",
        "중국" to "북경")

citiesMap["한국"] = "서울특별시"
citiesMap["미국"] = "워싱턴"

for ((k,v) in map) {
    println("$k -> $v")
}

// 집합: 중복되지 않는 요소들로 구성된 자료구조
val citySet = setOf("서울","수원","부산") // 읽기 전용

val citySet2 = mutableSetOf("서울", "수원", "부산") // 변경 가능
citySet2.add("안양")
citySet2.remove("수원")

println(citySet2.size)
println(citySet2.contains("서울"))

/*** 람다식 ***/
/* 람다식: 하나의 함수를 표현하는 방법으로 익명 클래스나 익명 함수를 간결하게 표현할 수 있어서 매우 유용함 */
var add = { x: Int,y: Int -> x +y }

println(add(2,5))

// SAM 변환: 추상 메서드 하나를 인수로 사용할 때는 ㅎ마수를 인수로 전달하면 편함. 자바로 작성된 메서드가 하나인 인터페이스를 구현할 때는 함수를 작성할 수 있음
button.setOnClickListener({ v: View? ->
    // 일반 람다식
})

button.setOnClickListener {
    it.visibility = View.GONE // 람다식에서 인수가 하나인 경우에는 모두 생략하고 람다 블록 내에서 인수를 it으로 접근할 수 있음
}


/*** 기타 기능 ***/
// 확장 함수
fun Int.isEven() = this % 2 == 0

println(aa.isEven())

// 형변환
val d = 10L
val cc = d.toInt()
val intStr = "10"
val str = Integer.parseInt(intStr)

val animal = dog as Animal

// 형 체크
if (str is String) {
    println(str.uppercase())
}

// 고차 함수: 함수의 인수로 함수를 전달하거나 함수를 반환하는 함수
fun add(x: Int, y: Int, callback: (sum: Int) -> Unit) {
    callback(x + y)
}

add(5,3, {println(it)}) // 함수는 { } 로 감싸고 내부에서는 반환값을 it로 접근할 수 있음

// 동반 객체: 안드로이드 프래그먼트 컴포넌트를 다룰 때 자동으로 동반 객체 코드가 생성됨, 프래그먼트는 팩토리 메서드 (생성자가 아닌 메서드를 사용해 객체를 생성하는 코딩 패턴)를 정의하여 인스턴스를 생성해야 함. 이떄 static 대신 (코틀린에서 지원하지 않음) 이 때 동반 객체로 구현함
class Fragment {
    companion object {
        fun newInstance(): Fragment {
            println("생성됨")
        }
    }
}

val fragment = Fragment.newInstance()

// let() 함수: 블록에 자기 자신을 인수로 전달하고 수행된 결과를 반환
val result = str?.let {
    Integer.parseInt(it)
}

// with() 함수: 인수로 객체를 받고 블록에 리시버 객체로 전달함
with(str) {
    println(toUpperCase())
}

// apply() 함수: 블록에 객체 자신이 리시버 객체로 전달되고 이 객체가 반환됨. 객체의 상태를 변화시키고 그 객체를 다시 반환할 떄 주로 사용
val result = car?.apply {
    car.setColor(Color.RED)
    car.setPrice(1000)
}

/// run() 함수: 익명 함수처럼 사용하는 방법과 ,객체에서 호출하는 방법을 모두 제공함
val avg = run {
    val korean = 100
    val english = 80
    val math = 50

    (korean + english + math) / 3.0
}

str?.run {
    println(toUpperCase())
}