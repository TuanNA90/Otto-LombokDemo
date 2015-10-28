## Otto-LombokDemo
*Demo an example about otto and lombok in a project*
  * [Otto] is an Open Source project designed to provide an event bus implementation <br/>so that components can publish and subscribe to events.
  * [Lombok] is used to reduce boilerplate code for model/data objects, e.g., <br/>it can generate getters and setters for those object automatically by using Lombok annotations.

## Guide install Otto and Lombok into project
*into /app and open file build.gradle and insert commands below:*
######1. Otto
  ```
    dependencies {
      compile 'com.squareup:otto:1.3.8'
    }
  ```
######2.Lombok
  ```
    dependencies {
        provided 'org.projectlombok:lombok:1.12.6'
        apt "org.projectlombok:lombok:1.12.6"
    }
  ```
## How to using Otto and Lombok
######1. Otto
  * *Create a singleton class for the bus using the [@EBean]() annotation.*
```
    @EBean(scope = Scope.Singleton)
    public class BusProvider extends Bus {}
```
  * *Event receivers must register via the [register()]() method of the Bus class.*
```
    @Override
    public void onResume() {
        super.onResume();
        busProvider.register(this);
    }
```
  *  *To unregister from events use the [unregister()]() method.*
```
    @Override
    public void onPause() {
        super.onPause();
        busProvider.unregister(this);
    }
```
  * *Create the event class that will transit through the bus.*
```
    @AllArgsConstructor(suppressConstructorProperties = true)
    public class BusArrayListWord {
        @Getter
        private ArrayList<Word> wordArrayList;
    }
```
  * *Post a new event to the bus: [bus.post( ...)]()*
```
    BusProvider.getInstance().post(new BusArrayListWord(wordLists));
```
  * *If new components, like a dynamically created fragment, should receive event data during their creation,<br/> components can register as producer for such event data with the [@Produce]() annotation.*
```
    @Produce
    public BusArrayListWord produceArrayListWord() {
        return new BusArrayListWord(wordLists);
    }
    
    BusProvider.getInstance().post(produceArrayListWord());
```
  * *Use [@Subscribe]() annotation to get the published events.*
```
    @Subscribe
    public void onReceiverArrayListWord(BusArrayListWord event) {
        Log.d("xxx", "Nhan WordLists: " + event.getWordArrayList());
    }
```
######2. Lombok
 * *The easiest way is to use the @Data annotation.*
<br/>[*@Data*]() generates all the boilerplate that is normally associated with simple POJOs (Plain Old Java Objects) <br/>and beans: *getters for all fields, setters for all non-final fields, and appropriate toString, equals* 
<br/>and *hashCode* implementations that involve the fields of the class.
```
    @Data
    @NoArgsConstructor
    public class Word {
        private int id;
        private String name;
        private String phonetic;
        private String meaning;
    }
```
 * *@Builder*
<br/>[*@Builder*]() lets you automatically produce the code required to have your class be instantiable with code such as:<br/>
```
    Word.builder()
        .id(in.readInt())
        .name(in.readString())
        .phonetic(in.readString())
        .meaning(in.readString())
        .build();
```
 * @NoArgsConstructor, @RequiredArgsConstructor, @AllArgsConstructor
  * [*@NoArgsConstructor*]() will generate a constructor with no parameters.
```
    @NoArgsConstructor
    public class NoArgsExample {
      private String field;
    }
```
  * 
   * [*@RequiredArgsConstructor*]() generates a constructor with 1 parameter for each field that requires special handling.
  * 
   * [*@AllArgsConstructor*]() generates a constructor with 1 parameter for each field in your class.
   * [*@java.beans.ConstructorProperties*]() annotation is added for all constructors with at least 1 argument, which allows bean editor tools to call the generated constructors. 
   * [*suppressConstructorProperties*]() if set to  **true**, then lombok will skip adding a[ @java.beans.ConstructorProperties]() to generated constructors.
```
    @AllArgsConstructor(suppressConstructorProperties = true)
    public class BusArrayListWord {
        @Getter
        private ArrayList<Word> wordArrayList;
    }
```
