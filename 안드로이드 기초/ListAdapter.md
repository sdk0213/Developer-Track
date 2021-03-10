### [선수 지식 - DiffUtil](https://github.com/sdk0213/Developer-Track/blob/master/안드로이드%20기초/RecyclerView-adapter-DiffUItil.md)
### [선수 지식 - AAC](https://github.com/sdk0213/Developer-Track/tree/master/안드로이드%20디자인%20패턴)
# ListAdapter
### 정의
* AsyncListDiffer를 내부적으로 사용하고 있는 클래스
* 추상클래스이다.
* RecyclerView, AsyncListDiffer를 사용하고있다.
  * ```java
    public abstract class ListAdapter<T, VH extends RecyclerView.ViewHolder> extends RecyclerView.Adapter<VH> {
  
      final AsyncListDiffer<T> mDiffer;
      private final AsyncListDiffer.ListListener<T> mListener = new AsyncListDiffer.ListListener<T>() {
          @Override
          public void onCurrentListChanged(@NonNull List<T> previousList, @NonNull List<T> currentList) {
                  ListAdapter.this.onCurrentListChanged(previousList, currentList);
          }
      };
    
      ...
      ..
      .
    }
---
### 사용
* ```kotlin
  class PersonListAdapter : ListAdapter<Person, PersonViewHolder>(diffUtil) {
      
      override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = PersonViewHolder(
          ItemPersonBinding.inflate(LAyoutInflater.from(parent.context), parent, false)
      }
      
      override fun onBindViewHolder(holder: PersonViewHolder, position: Int) {
          holder.bind(getItem(position))
      }
      
      fun replaceItems(items: List<Person>) {
          submitList(items)
      }
      
      companion object {
          val diffUtil = obejct: DiffUtil.ItemCallback<Person>() {
              override fun areContentsTheSame(oldItem: Person, newItem: Person_) = 
                    oldItem == newItem
              override fun areItemsTheSame(oldItem: Person, newItem: Person) =
                    oldItem.name == newItem.name
          }
      }
      
   ...
   ..
   .
   
   val personAdapter = PersonListAdpater()
   personAdapter.submitList<newItems)
   
   
# Google Architectrue BasicSample 예제
### 생성
* ```java
  public class CommentAdapter extends ListAdapter<CommentEntity, CommentAdapter.CommentViewHolder> {

    @Nullable
    private final CommentClickCallback mCommentClickCallback;

    CommentAdapter(@Nullable CommentClickCallback commentClickCallback) {
        super(new AsyncDifferConfig.Builder<>(new DiffUtil.ItemCallback<CommentEntity>() {
            @Override
            public boolean areItemsTheSame(@NonNull CommentEntity old,
                    @NonNull CommentEntity comment) {
                return old.getId() == comment.getId();
            }

            @Override
            public boolean areContentsTheSame(@NonNull CommentEntity old,
                    @NonNull CommentEntity comment) {
                return old.getId() == comment.getId()
                        && old.getPostedAt().equals(comment.getPostedAt())
                        && old.getProductId() == comment.getProductId()
                        && TextUtils.equals(old.getText(), comment.getText());
            }
        }).build());
        mCommentClickCallback = commentClickCallback;
    }
* super에 직접 AsyncDifferConfig 생성 및 콜백 설정하였음 -> 왜 저런식으로 익명으로 생성하였는지는 의문
### binding
##### onCreateViewHolder
* ```java
  public CommentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
      CommentItemBinding binding = DataBindingUtil
              .inflate(LayoutInflater.from(parent.getContext()), R.layout.comment_item,
                      parent, false);
      binding.setCallback(mCommentClickCallback);
      return new CommentViewHolder(binding);
  }
##### ViewHolder
* ```java
  static class CommentViewHolder extends RecyclerView.ViewHolder {
      final CommentItemBinding binding;
      CommentViewHolder(CommentItemBinding binding) {
          super(binding.getRoot());
          this.binding = binding;
      }
  }
##### onBindViewHolder
* ```java
  @Override
  public void onBindViewHolder(@NonNull CommentViewHolder holder, int position) {
      holder.binding.setComment(getItem(position));
      holder.binding.executePendingBindings();
  }
  
    
    
