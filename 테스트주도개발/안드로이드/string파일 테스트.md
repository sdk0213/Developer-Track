> xml의 R.string.. 테스트하기
* ```java
  import android.support.test.rule.ActivityTestRule;
  import android.support.test.runner.AndroidJUnit4;
  import android.test.suitebuilder.annotation.LargeTest;
  import org.junit.Before; 
  import org.junit.Rule;
  import org.junit.Test; 
  import org.junit.runner.RunWith; 
  import static org.hamcrest.CoreMatchers.is; 
  import static org.junit.Assert.assertThat;

  @RunWith(AndroidJUnit4.class
  public class TestString { 
    private MainActivity activity;
 
    @Rule
    public ActivityTestRule<MainActivity> mActivityRule = new ActivityTestRule<MainActivity>(MainActivity.class);

    @Before
    public void setUp() {
      this.activity = mActivityRule.getActivity();
    } 

    @Test
    public void testString() {
      String str = mActivityRule.getActivity().getString(R.string.testStr); 
      assertThat("hihihi", is(str)); 
    } 
  }
