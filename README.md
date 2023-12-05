### Google Guava 的Preconditions类各种用法

Preconditions类_提供_静态方法列表，用于检查是否使用有效参数值调用方法或构造函数。如果前提条件失败，则会抛出定制的异常。

#### 前置依赖
引入 pom
```xml
<dependency>
  <groupId>com.google.guava</groupId>
  <artifactId>guava</artifactId>
  <version>32.1.3-jre</version>
</dependency>
```

#### checkArgument

Preconditions类的checkArgument方法用于校验传递给调用方法的参数的正确性。该方法接收一个布尔条件，当条件为false时，抛出IllegalArgumentException异常。
让我们通过一些示例来看看如何使用这个方法。

```java
import com.google.common.base.Preconditions;

public class ExampleClass {
    public void processNumber(int number) {
        Preconditions.checkArgument(number > 0, "Number must be positive");
        // 进行处理逻辑
    }

    public static void main(String[] args) {
        ExampleClass example = new ExampleClass();
        example.processNumber(10); // 正常调用，参数符合条件

        example.processNumber(-5); // 抛出IllegalArgumentException异常，参数不符合条件
    }
}
```

抛出带有模板的错误信息
```java
public class PreconditionsTest {
    public void processNumber(int number) {
        Preconditions.checkArgument(number > 0, "Number must be positive: %s", number);
        // 进行处理逻辑
    }
    @Test
    public void testCheckArgumentTemplate() {
        processNumber(10);// 正常调用，参数符合条件
        processNumber(-5); // 抛出IllegalArgumentException异常，参数不符合条件，异常消息中包含具体的参数值
    }
}
```
在上述示例中，我们在checkArgument方法的第二个参数中使用了带有占位符的消息模板："Number must be positive: %s"。该占位符%s表示在抛出异常时会被具体的参数值替换。在调用processNumber方法时，如果传递的参数不满足条件，将抛出IllegalArgumentException异常，并在异常消息中包含具体的参数值。

#### checkElementIndex
Preconditions类的checkElementIndex方法用于验证索引是否包含在集合中，如果不包含，该方法将抛出IndexOutOfBoundsException异常
``` java
   @Test
    public void testCheckElementIndex(){
        ArrayList<String> list = new ArrayList<>();
        list.add("apple");
        list.add("banana");
        list.add("orange");

        int index = 3;
        Preconditions.checkElementIndex(index, list.size(), "Invalid index");

        String element = list.get(index);
        System.out.println("Element at index " + index + ": " + element);
    }
```
在本例中，我们使用集合的size作为集合的大小参数，并提供了错误消息`Invalid index`。如果索引超出了集合的有效范围，将抛出异常并显示错误消息。
如果在范围内，则使用list.get方法获取指定索引的元素，并将其打印出来。

#### checkNotNull
checkNotNull方法检查作为参数提供的值是否为 null。如果不为 null 则返回校验后的值。如果传递给此方法的值为 null，则抛出NullPointerException。
以下是一个示例，说明了checkNotNull方法的使用：
```java
  @Test
    public void testCheckNotNull(){
        String name = "John Doe";
        Preconditions.checkNotNull(name, "Name cannot be null");
        System.out.println("Name: " + name);
    }
```
在此示例中，我们使用checkNotNull方法验证字符串name是否为null。如果name不为null，将继续处理；如果name为null，将抛出NullPointerException异常，并显示错误消息"Name cannot be null"。


### checkState
Preconditions类的checkState方法用于验证对象或程序的状态是否符合预期。以下是一个示例，说明了checkState方法的使用：
```java
  @Test
    public void testCheckState() {
        int[] validStates = {-1, 0, 1};
        int givenState = 8;
        String message = "You have entered an invalid state";
        Preconditions.checkState(Arrays.binarySearch(validStates, givenState) > 0, message);
    }
```
在此示例中，我们使用checkState方法验证givenState是否包含在validStates数组中。如果false，则抛出IllegalStateException，并显示信息为`You have entered an invalid state`，否则继续处理；




