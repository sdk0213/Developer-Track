* flavor 이름을 Implementation(imple..에서 i가 대문자(I)임) 앞에다가 붙이기 
```gradle
flavorName1Implementation 'com.library1..'
flavorName2Implementation 'com.library2..'
flavorName3Implementation fileTree(include: ['*.jar', '*.aar'], dir: 'libs/libraryfile')
