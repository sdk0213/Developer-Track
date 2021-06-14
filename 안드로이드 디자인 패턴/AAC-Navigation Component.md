# Navigation Component
* 사용자가 앱 내의 화면에서 다르 화면으로 이동하고, 돌아오느 상호 작용을 말한다.
---
### 사용했을때의 장점
* fragment transaction 불필요
* 목적지 간 이동 시 복귀를 올바르게 수행
* 기본적으로 화면 전환 애니메이션 제공해준다.
* 딥 링크 구현 및 처리 제공
* 최소한의 추가작업으로 서랍, 보텀 같은 UI 패턴을 구현
* 목적지 간 인자 전달 시 type-safety을 위해 Safe Args를 Gradle Plugin으로 제공
* ViewModel 범위를 Navigation Graph로 지정하여 그래프 내 목적지 간 UI 관려 데이터를 공유 가능하다.
---
### 구성요소
* Navigation Graph
  * 화면 간 **이동 관계를 한군데 모아 나타내는 XML** 리소스
* NavHost
  * **그래프의 모든 목적지를 표시**하는 빈 컨테이너
* NavController
  * NavHost 내에서 사용자의 액션에 의해 **목적지를 변경하는 것을 관리**한다.
---
### Navigation Graph
* **모든 탐색 경로 시각화**
* 목적지/액션 모두를 포함하는 리소스 파일
* google sunflower example
  ```xml
  <navigation xmlns:android="http://schemas.android.com/apk/res/android"
      xmlns:app="http://schemas.android.com/apk/res-auto"
      xmlns:tools="http://schemas.android.com/tools"
      app:startDestination="@id/view_pager_fragment">

      <fragment
          android:id="@+id/view_pager_fragment"
          android:name="com.google.samples.apps.sunflower.HomeViewPagerFragment"
          tools:layout="@layout/fragment_view_pager">
  
          <action
                  android:id="@+id/action_view_pager_fragment_to_plant_detail_fragment"
                  app:destination="@id/plant_detail_fragment"
                  app:enterAnim="@anim/slide_in_right"
                  app:exitAnim="@anim/slide_out_left"
                  app:popEnterAnim="@anim/slide_in_left"
                  app:popExitAnim="@anim/slide_out_right" />
      </fragment>

      <fragment
                ...
      </fragment>
  
      <fragment
                ...
      </fragment>

  </navigation>
---
### NavHost
* 사용자가 앱을 탐색할 때 목적지가 전환되는 빈 컨테이너이다.
* 그러니까 그냥 activity내의 navigation Component를 위한 공간이라고 생각하면된다.
* google sunflower example
  * ```xml
    <layout
        xmlns:android="http://schemas.android.com/apk/res/android"
        xmlns:app="http://schemas.android.com/apk/res-auto">

        <androidx.fragment.app.FragmentContainerView
            android:id="@+id/nav_host"
            android:name="androidx.navigation.fragment.NavHostFragment" <!-- NavHostFragment 랑 연결-->
            android:layout_width="match_parent"
            android:layout_height="match_parent" 
            app:defaultNavHost="true" <!--시스템이 Back Press 이벤트르 가로채도록 유도하며 여러개의 NavHost를 구현할경우 하나만 이 값을 true로 해야한다.-->
            app:navGraph="@navigation/nav_garden" /> <!-- nav_graph와 연결 -->

    </layout>
---
### 목적지로의 이동
##### 시작목적지 정하기
* **사용자에게 나타나는 첫 번째 화면이며, 앱을 종료할때 사용자에게 표시되는 마지마 화면**
* <img width="376" alt="스크린샷 2021-04-25 오후 8 43 47" src="https://user-images.githubusercontent.com/51182964/115992124-fe378c80-a606-11eb-94a1-1e9d312f1dd4.png">
##### 액션
* 목적지 사이의 논리적인 연결 관계
* 탐색을 수행할 코드를 작성해야함
* <img width="419" alt="스크린샷 2021-04-25 오후 8 55 34" src="https://user-images.githubusercontent.com/51182964/115992444-c16c9500-a608-11eb-9310-5309f1d619d2.png">
##### 목적지 이동
* NavController 사용하여 목적지로 이동하는 방법은 보통 다음과 같다.
  * kotlin
    ```kotlin
    Fragment.findNavController()
    View.findNavController()
    Activity.findNavController(viewId: Int)
  * java
    ```java
    NavHostFragment.findNavController(fragment)
    Navigation.findNavController(Activity, @IdRes int viewId)
    Navigation.findNavController(View)
##### 백스택
* popUpToInclusive : true 
  * popUpTo으로 설정한 fragment까지 백스택에서 제거를 하고, popUpTo으로 설정한 fragment의 이전 fragment로 이동을한다.
* popUpToInclusive : false 
  * popUpTo으로 설정한 fragment 이전까지만 백스택에서 제거를 하고, popUpTo으로 설정한 fragment로 이동한다.
---
### 목적지간 데이터 전달은 Safe Args 사용
* Safe Args는 목적지 간 이동시 안전하게 인자를 전달하도록 객체 및 빌더 클래스를 제공한다.
* implements와 apply plugin 그리고 androidx 활성화 옵션이 필요하다.
* Safe Args가 활성화되면 액션에 대한 클래스와 메서드가 포험된 코드를 생성한다.
  * 생성되느 이름은 FragmentA 일경우 FragmentADirectons 이라는 클래스이다. 
##### safe args 사용안할시
* ```kotlin
  // send
  findNavController().navigate(R.id.action_to_gameFragment, bundleOf("itemId" to 15))
  ...
  // received
  arguments?.getInt("itemId")!!
##### safe args 사용
* ```xml
  // global build.gradle
  dependencies {
     classpath 'com.android.tools.build:gradle:4.2.1'
     classpath "org.jetbrains.kotlin:kotlin-gradle-plugin:1.5.10"
     classpath "androidx.navigation:navigation-safe-args-gradle-plugin:2.3.5"

     // NOTE: Do not place your application dependencies here; they belong
     // in the individual module build.gradle files
  }
 
  // local build.gradle
  plugins {
     id "androidx.navigation.safeargs.kotlin" // kotlin 일때 java는 다르다
  } 
* xml 
  ```xml
   <fragment
     android:id="@+id/view_pager_fragment"
     ...">

     <action
             android:id="@+id/action_view_pager_fragment_to_plant_detail_fragment"
             app:destination="@id/plant_detail_fragment"
             ... />
   </fragment>

   <fragment
       android:id="@+id/plant_detail_fragment"
       ...">

       <action
           android:id="@+id/action_plant_detail_fragment_to_gallery_fragment"
           app:destination="@id/gallery_fragment"
           ... />
       <argument
           android:name="plantId"
           app:argType="string" />
    </fragment>  
* send
  ```kotlin
  val direction = HomeViewPagerFragmentDirections
       .actionViewPagerFragmentToPlantDetailFragment(plantId)
  view.findNavController().navigate(direction)
* received
  ```kotlin
  private val args: PlantDetailFragmentArgs by navArgs()
  // 부연설명
  // The approach gives you a lazily created Args instance instead, 
  // which will only be initialized when you first try reading its value. 
  // Therefore it's safe to declare it at the class level. 
  // See navArgs in the docs for all the details. The most important parts:
  // more info : github - https://stackoverflow.com/questions/57034392/what-is-the-difference-between-by-navargsscorefragmentargs-vs-scorefragme
  
  // 또는 다음과 같으 형태의 코드로 사용
  var args = GameWonFragmentArgs.fromBundle(arguments!!)


  
