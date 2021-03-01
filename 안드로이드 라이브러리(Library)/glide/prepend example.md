[참고 github](https://github.com/TempoDiValse/GlidePDF)
[참고 블로그](https://blog.naver.com/jiwow34)

GlideModule(선언)
---
* ```java
  @com.bumptech.glide.annotation.GlideModule
  public class GlideModule extends AppGlideModule {

    @Override public boolean isManifestParsingEnabled() {
        return super.isManifestParsingEnabled();
    }

    @Override public void applyOptions(@NonNull Context context, @NonNull GlideBuilder builder) {
        super.applyOptions(context, builder);
    }

    @Override public void registerComponents(@NonNull Context context, @NonNull Glide glide, @NonNull Registry registry) {
        super.registerComponents(context, glide, registry);
        registry.prepend(File.class, ByteBuffer.class, new CustomModelLoader(context));
    }
  }
  

CustomModelLoader(모델로더)
---
* ```java
  public class CustomModelLoader implements com.bumptech.glide.load.model.ModelLoader<File, ByteBuffer> {
    private Context context;
    CustomModelLoader(Context context){
        this.context = context;
    }

    @Nullable
    @Override
    public LoadData<ByteBuffer> buildLoadData(@NonNull File file, int width, int height, @NonNull Options options) {
        CustomDataFetcher gfddf = new CustomDataFetcher(context, file);
        ObjectKey objectKey = new ObjectKey("File: " +file.getName());
        return new LoadData<>(objectKey, gfddf);
    }

    @Override
    public boolean handles(@NonNull File file) {
        return true;
    }
  }
  
CustomModelFactory(모델로더 처리)
---
* ```java
  public class CustomModelFactory implements ModelLoaderFactory<File, ByteBuffer> {
    private Context context;
    CustomModelFactory(Context context){
        this.context = context;
    }
    @NonNull
    @Override
    public com.bumptech.glide.load.model.ModelLoader build(@NonNull MultiModelLoaderFactory multiFactory) {
        return new CustomModelLoader(context);
    }

    @Override
    public void teardown() {

    }
  }
  
CustomDataFetcher(데이터 처리)
---
* ```java
  public class CustomDataFetcher implements com.bumptech.glide.load.data.DataFetcher<ByteBuffer> {

    private Context context;
    private File file;
    private String androidIdKey;
    CustomDataFetcher(Context context, File file){
        this.context = context;
        this.file = file;
    }
    @Override
    public void loadData(@NonNull Priority priority, @NonNull DataCallback<? super ByteBuffer> callback) {
      // Context를 사용해서 데이터 처리
      // 콜백넘기기
    }

    @Override
    public void cleanup() {

    }

    @Override
    public void cancel() {

    }

    @NonNull
    @Override
    public Class<ByteBuffer> getDataClass() {
        return ByteBuffer.class;
    }

    @NonNull
    @Override
    public DataSource getDataSource() {
        return DataSource.LOCAL;
    }
  }
