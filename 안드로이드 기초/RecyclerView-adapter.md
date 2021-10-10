RecyclerView-Aadapter
===
출처 : https://recipes4dev.tistory.com/m/154
출처 : <https://ppizil.tistory.com/25>

ListView와 RecyclerView
---
+ 공통점
  + 화면에 보이게 되는 영역의 아이템만을 인플레이트를 시도한다.
  + 나머지는 후에 화면 화면에 보일때 로드한다.
+ 차이점
  + 이미 만들어진 뷰를 "재사용(Recycle)"을 하냐의 차이이다.
+ **그래서**
  + 이 개념(Viewholder)을 강제할것이냐 아니냐의 차이가 바로 Listview와 RecylcerView의 차이이다.
  + ListView + Flexibility + Performance <=> RecyclerBiew
  + RecyclerView는 구글이 뷰홀더 패턴을 항상 코드로 강제하도록 라이브러리로 만든것이다.

RecyclerView
---
* 리사이클러뷰(RecyclerView)는 "사용자가 관리하는 많은 수의 **데이터 집합(Data Set)을 개별 아이템 단위로 구성하여 화면에 출력하는 뷰그룹(ViewGroup)**이며, 한 화면에 표시되기 힘든 많은 수의 데이터를 **스크롤 가능한 리스트로 표시해주는 위젯**"입니다.
* 리사이클러뷰(RecyclerView) = Listview + 유연함(Flexibility) + 성능(Performance)

Viewholder
---
* android.support.v7.widget.RecyclerView.ViewHolder
* 뷰홀더(ViewHolder)는 화면에 **표시될 아이템 뷰를 저장하는 객체**입니다.
+ Listview에서 findviewById는 호출 비용이 부담스러워서 사용되는 패턴이다.
  + ListView의 Performance를 높인다.
+ xml view와 Data를 담아줄수 있는것

코드 작성
---
> onCreateViewHolder(ViewGroup parent, int viewType)
* viewType 형태의 아이템 뷰를 위한 뷰홀더 객체 생성
* ```java
  //onCreateViewHolder() - 아이템 뷰를 위한 뷰홀더 객체 생성하여 리턴.
  @Override
  public SimpleTextAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
      Context context = parent.getContext() ;
      LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) ;

      View view = inflater.inflate(R.layout.recyclerview_item, parent, false) ;
      SimpleTextAdapter.ViewHolder vh = new SimpleTextAdapter.ViewHolder(view) ;

      return vh ;
  }
> onBindViewHolder(ViewHolder holder, int position)
* position에 해당하는 데이터를 뷰홀더의 아이템뷰에 표시
* ```java
  // onBindViewHolder() - position에 해당하는 데이터를 뷰홀더의 아이템뷰에 표시.
  @Override
  public void onBindViewHolder(SimpleTextAdapter.ViewHolder holder, int position) {
      String text = mData.get(position) ;
      holder.textView1.setText(text) ;
  }
> getItemCount()
* 전체 아이템 갯수 리턴
* ```java
  // getItemCount() - 전체 데이터 갯수 리턴
  @Override
  public int getItemCount() {
      return mData.size() ;
  }
> Viewholder class
* 아이템 뷰 저장
* ```java
  // 아이템 뷰를 저장하는 뷰홀더 클래스.
  public class ViewHolder extends RecyclerView.ViewHolder {
      TextView textView1 ;

      ViewHolder(View itemView) {
          super(itemView) ;

          // 뷰 객체에 대한 참조. (hold strong reference)
          textView1 = itemView.findViewById(R.id.text1) ;
      }
  }

Viewtype 지정 - [lktprogrammer님의 tistory](https://lktprogrammer.tistory.com/190)
---
* ```java
  // MainActivity부분 -> MainAcitivty에서 recyclerview adapter를 생성하고 viewtype을 지정해준다.
  
  public void initializeData()
   {
       dataList = new ArrayList<>();

       dataList.add(new DataItem("사용자1님이 입장하셨습니다.", null,  Code.ViewType.CENTER_CONTENT));
       dataList.add(new DataItem("사용자2님이 입장하셨습니다.", null,  Code.ViewType.CENTER_CONTENT));
       dataList.add(new DataItem("안녕하세요", "사용자1",  Code.ViewType.LEFT_CONTENT));
       dataList.add(new DataItem("안녕하세요", "사용자2",  Code.ViewType.RIGHT_CONTENT))
   }
* ```java
  // Enum 클래스를 사용하지 않고 클래스로 만든 이유는 궁금하다.
  public class Code {
    public class ViewType{
        public static final int LEFT_CONTENT = 0;
        public static final int RIGHT_CONTENT = 1;
        public static final int CENTER_CONTENT = 2;
    }
  }
* ```java
  public class DataItem {

    private String content;
    private String name;
    private int viewType;

    public DataItem(String content, String name ,int viewType) {
        this.content = content;
        this.viewType = viewType;
        this.name = name;
    }

    public String getContent() {
        return content;
    }

    public String getName() {
        return name;
    }

    public int getViewType() {
        return viewType;
    }
  }
* ```java
   // 이곳에서 position으로 viewtype을 가져오고 onBindViewHolder의 viewtype으로 보낸다.
  @Override
  public int getItemViewType(int position) {
      return myDataList.get(position).getViewType();
  }
* ```java
  // adapeter 부분
  public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
        {
            View view;
            Context context = parent.getContext();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            if(viewType == Code.ViewType.CENTER_CONTENT)
            {
                view = inflater.inflate(R.layout.center_content, parent, false);
                return new CenterViewHolder(view);
            }
            else if(viewType == Code.ViewType.LEFT_CONTENT
