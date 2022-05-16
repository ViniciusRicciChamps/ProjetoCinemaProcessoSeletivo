# ProjetoCinemaProcessoSeletivo
Projeto criado com etapa do processo seletivo para desenvolvedor Mobile Junior. A ideia do projeto é um app que consuma uma API que contem uma base de dados com descrição de filmes.



Foi implementado a opção de buscar Filmes, Lançamentos e  salvar nos favoritos. Como o tempo era muito pouco não deu para implementar ou ajustar algumas coisa.


********* OBS ************
Verificar a chave da API se ainda está dentro do prazo de uso (se houver data de vencimento) 
Utilizar a internet para usar.



    android {
        compileSdk 32 ATENÇÃO AO SDK

        defaultConfig {
            applicationId "com.example.projetocinemaprocessoseletivo"
            minSdk 21  ********** O MINIMO
            targetSdk 32
            versionCode 1
            versionName "1.0"

            testInstrumentationRunner "androidx.test.runner.AndroidJUnitRunner"
    }




    dependencies {

        implementation 'androidx.appcompat:appcompat:1.4.1'
        implementation 'com.google.android.material:material:1.6.0'
        implementation 'androidx.constraintlayout:constraintlayout:2.1.3'
        implementation 'com.google.ar.sceneform:filament-android:1.17.1'
        testImplementation 'junit:junit:4.13.2'
        androidTestImplementation 'androidx.test.ext:junit:1.1.3'
        androidTestImplementation 'androidx.test.espresso:espresso-core:3.4.0'
        implementation "androidx.cardview:cardview:1.0.0"
        implementation 'com.github.bumptech.glide:glide:4.13.0'
        implementation 'androidx.recyclerview:recyclerview:1.2.1'
        implementation 'com.squareup.retrofit2:retrofit:2.9.0'
        implementation 'com.squareup.retrofit2:converter-gson:2.9.0'


    }
