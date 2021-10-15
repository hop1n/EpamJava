1) На какие две группы разделяются классы, объявленные внутри другого класса?  
Как они называются на инглише?  
**Ответ** Nested Classes(вложенные классы), Inner Classes(внутренние классы).  
**Источник** https://docs.oracle.com/javase/tutorial/java/javaOO/nested.html  


2) Для каких целей они используются?  
**Ответ** В Java можно определить (вложить) один класс внутри определения другого класса, что позволяет группировать классы, логически связанные друг с другом, и динамично управлять доступом к ним. С одной стороны, обоснованное использование в коде внутренних классов делает его более эффектным и понятным. С другой стороны, применение внутренних классов есть один из способов сокрытия кода, так как внутренний класс может быть абсолютно недоступен и не виден вне класса-владельца. Внутренние классы также используются в качестве блоков прослушивания событий. Решение о включении одного класса внутрь другого может быть принято при слишком тесном и частом взаимодействии двух классов. Одной из серьезных причин использования внутренних классов является возможность быть подклассом любого класса независимо от того, подклассом какого класса является внешний класс. Фактически при этом реализуется ограниченное множественное наследование со своими преимуществами и проблемами. При необходимости доступа к защищенным полям и методам некоторого класса может появиться множество подклассов в разных пакетах системы. В качестве альтернативы можно сделать этот подкласс внутренним и методами класса-владельца предоставить доступ к интересующим защищенным полям и методам.  
**Источник** Java. Методы программирования : уч.-мет. пособие / И.Н. Блинов, В.С. Романчик. с.131  


3) Какие уровни доступа применяются к таким классам?  
**Ответ** public, protected, default, private.  
**Источник** https://docs.oracle.com/javase/tutorial/java/javaOO/nested.html  


4) Какие существуют варианты внутренних классов?  
**Ответ** Существуют четыре категории вложенных классов:  
a) статический класс-член (static member class),  
b) не статический класс-член (nonstatic member class),  
c) анонимный класс (anonymous class)  
d) локальный класс (local class).  
**Источник** Джошуа Блох - Effective Java (2nd ed. 2008) ст. 22  


5) Пусть объявлен класс Outer, а внутри него публичный вложенный класс NestedPublic и публичный внутренний класс InnerPublic. Создайте экземпляры каждого класса:  
а) внутри класса Outer,  
б) извне класса Outer?  
**Ответ**  
a) ``NestedPublic nestedPublic = new NestedPublic();``  
``InnerPublic innerPublic = new InnerPublic();``  
b) ``OuterClass.NestedPublic nestedPublic = new OuterClass.NestedPublic();``  
``OuterClass.InnerPublic innerPublic = new OuterClass().new InnerPublic();``  


6) Пусть объявлен класс Outer, а внутри него приватный вложенный класс NestedPrivate и приватный внутренний класс InnerPrivate. Создайте экземпляры каждого класса:  
а) внутри класса Outer,  
б) извне класса Outer?  
**Ответ**  
a) ``NestedPublic nestedPublic = new NestedPublic();``  
``InnerPublic innerPublic = new InnerPublic();``  
b) Нельзя  


7) Пусть объявлен класс Outer, а внутри него внутренний класс Inner. Как обратиться внутри класса Inner:  
а) к экземпляру класса Inner,  
б) к объемлющему экземпляру класса Outer?  
**Ответ**  
a) ``InnerPublic innerPublic = this;``  
b) ``OuterClass outerClass = OuterClass.this;``  


8) Пусть объявлен класс Outer, а внутри него вложенный класс Nested. Как обратиться внутри класса Nested:  
а) к экземпляру класса Nested,  
б) к объемлющему экземпляру класса Outer?  
**Ответ**  
a) ``NestedPublic nestedPublic = this;``  
b) ``OuterClass outerClass = new OuterClass();``  


9) Можно ли из вложенного класса обратиться к членам внешнего класса?  
Если да, то приведите пример.  
**Ответ** Только если передать их в списке параметров, либо создать экземпляр внешнего класса.  
**Источник**   


10) Можно ли из внутреннего класса обратиться к экземпляру внешнего класса?  
Если да, то приведите пример.  
**Ответ** Да.  
```java
public class OuterClass {
   private String name = "name";
   private class InnerPublic {
       void demo(){
           String s = name;
   }
}
```


11) Можно ли определить экземпляр вложенного класса, не определяя экземпляры внешнего класса?  
Если да, то приведите пример.  
**Ответ** Да.  
``OuterClass.NestedPublic nestedPublic = new OuterClass.NestedPublic();``


12) Есть ли ограничения на объявление локальных переменных в локальных внутренних классах?  
Есть ли да, то какие?  
**Ответ** Нельзя объявлять их static, но можно static final.  
**Источник**   


13) Можно ли наследовать вложенные классы?  
Если да, то приведите пример.  
**Ответ** Можно.  
```java
public class OuterClass {
   static class NestedPublic {

   }
   static class NestedPublicInherited extends NestedPublic{
     
   }
}
```  


14) Можно ли из подкласса обратиться к методу вложенного суперкласса?  
Если да, то приведите пример.  
**Ответ** Да.  
```java
public class OuterClass {
   static class NestedPublic {

       void demo(){

       }
   }
   static class NestedPublicInherited extends NestedPublic{
       @Override
       void demo() {
           super.demo();
       }
   }
}
 ```  


15) Какие существуют варианты внутренних интерфейсов?  
**Ответ** public, private или protected.  
**Источник**   


16) Можно ли объявить класс внутри интерфейса?  
Если да, то есть ли ограничения? Приведите пример.  
**Ответ** Он автоматически статический и ``public``.  
```java
public interface Inter {
    public static class InnerPublic {
    	void displayState() {
            System.out.println("InnerPublic");
    	}
	}
}
```  


17) Можно ли создать экземпляр анонимного класса на основе:  
а) абстрактного класса?  
б) интерфейса?  
в) неабстрактного класса?  
г) финального класса?  
Если да, то приведите пример.  
**Ответ**  
a) да  
b) да  
c) да  
d) нет  


18) Дан следующий java-файл.  
```java
//-------------- begin --------------
class Runner {
public static void main(String[] args){
Something something = new Something();
something.doSomething(...);		//1
}
}
interface Smthable {
void doSmth(); 
}
class Something {
	void doSomething(...) {			//2
		smth.doSmth();
}
}
//--------------- end ---------------
```
1. Замените многоточия в строках 1 и 2 на такой код, чтобы приложение после запуска с помощью экземпляра анонимного класса, порожденного от интерфейса Smthable, вывело на консоль текст Hello, World.  
2. Получите тот же результат, переместив:  
а) интерфейс Smthable внутрь класса Something,  
б) класс Something внутрь интерфейса Smthable.  
**Ответ**  
```java
// 1) 
class Runner {
   public static void main(String[] args){
       Something something = new Something();
       something.doSomething(() -> {
           System.out.println("Hello World!");
       });       //1
   }
}
interface Smthable {
   void doSmth();
}
class Something {
   void doSomething(Smthable smth) {        //2
       smth.doSmth();
   }
}
 //2) 
// a) Тот же код, что и в 1.
// b) 
package socket;
class Runner {
   public static void main(String[] args){
       Smthable.Something something = new Smthable.Something();
       something.doSomething(() -> {
           System.out.println("Hello World!");
       });
   }
}
interface Smthable {
   class Something {
       void doSomething(Smthable smth) {
           smth.doSmth();
       }
   }
   void doSmth();
}
```


19) Дан следующий java-файл.  
```java
//-------------- begin --------------
abstract class AbstractRunner {
abstract int getYear();
abstract class AbstarctInner {
	abstract int getYear();
}
public static void main(String[] args) {
	... //1
	... //2
	... //3
}
}
//--------------- end ---------------
```
Создайте в строке 1 ссылку runner на экземпляр подкласса класса AbstractRunner.  
Создайте в строке 2 ссылку inner на экземпляр подкласса класса AbstractInner.  
Выведите на консоль в строке 3 текст 2010;2015, используя данные ссылки.  
**Ответ.**  
```java
abstract class AbstractRunner {
   abstract int getYear();
   abstract class AbstractInner {
       abstract int getYear();
   }
   public static void main(String[] args) {
       AbstractRunner abstractRunner = new AbstractRunner() {
           @Override
           int getYear() {
               return 2010;
           }
       };
       AbstractInner abstractInner = abstractRunner.new AbstractInner(){
           @Override
           int getYear() {
               return 2015;
           }
       };
       System.out.println(abstractRunner.getYear()+";"+abstractInner.getYear());
   }
}

```  

20) Дан следующий java-файл.  
```java
//-------------- begin --------------
class Runner {
	public static void main(String[] args) {
		... 	//1
	}
}
class Outer {
	class Inner {
		void go() {
			System.out.println("Gone!");
		}
	}
}	
//--------------- end ---------------
```
1. С помощью функционала классов Outer и Inner выведите на консоль в строке 1 текст Gone!.  
2. Переместив класс Outer внутрь класса Runner, получите тот же результат:  
а) не изменяя строку 1.  
б) изменяя строку 1,  
**Ответ**  
1)``new Outer().new Inner().go();``  
2).
a) Сделать класс ``Outer` статическим.  
b) ``new Runner().new Outer().new Inner().go();``  

 
**Источник**   


21) Что представляют собой элементы перечисления?  
Подсказка. Откомпилируйте фабричный класс из задачи inheritance1 и посмотрите, какие получились .class-файлы  
**Ответ** Объявление enum указывает новый тип перечисления, особый тип типа класса. 
Таким образом, из документации Oracle следует, что перечисления являются классами. 
Если объявить enum внутри другого класса, то enum будет внутренним классом. И перечисления всегда статичны, поэтому справедливо назвать enum статическим внутренним классом (или вложенным классом), когда он объявлен в другом классе.  
**Источник** https://docs.oracle.com/javase/specs/jls/se8/html/jls-8.html#jls-8.9%23jls-8.9  


22) Как образуются имена вложенных и внутренних .class-файлов после компиляции?  
Приведите примеры.  
**Ответ**  
```java
class Runner {
   static class Runner$Nested{
       void demo() {
       }
   }
}
```  