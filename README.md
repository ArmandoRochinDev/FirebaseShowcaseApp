# FirebaseShowcaseApp
FirebaseShowcaseApp es una aplicacion de ejemplo para mostrar Firebase Auth, FirebaseCloudMessaging, FireStore, FireStorage, Analitycs, Crashlitycs, MVVM, Clean Architecture, Flows, LiveData, ViewState.

## Firebase
<a href="https://firebase.google.com/">Firebase</a> es una plataforma de desarrollo de apps que te ayuda a compilar y desarrollar las apps y los juegos que les encantan a los usuarios. Con el respaldo de Google y la confianza de millones de empresas de todo el mundo. 

## Features
* Clean Architecture + MVVM Architecture.
* Jetpack Libraries and Architecture Components.

## Prerrequisitos
Agrega [Firebase](https://firebase.google.com/docs/android/setup) al proyecto de Android, en tu proyecto firebase es necesario tener activo:
```
Authentication
Firestore Database
Storage
Messaging
Crashlytics
Analytics
```

## Librerias
* [Firebase](https://firebase.google.com/)
  * [Auth](https://firebase.google.com/docs/auth) Firebase Authentication proporciona servicios de backend, SDK fáciles de usar y bibliotecas de IU ya elaboradas para autenticar a los usuarios en tu app.
  * [Firestore](https://firebase.google.com/docs/firestore) Cloud Firestore es una base de datos flexible y escalable para desarrollo móvil, web y de servidores de Firebase y Google Cloud. 
  * [Storage](https://firebase.google.com/docs/storage) Cloud Storage para Firebase es un servicio de almacenamiento de objetos potente, simple y rentable construido para el escalamiento de Google. 
  * [FCM](https://firebase.google.com/docs/cloud-messaging) Firebase Cloud Messaging (FCM) es una solución de mensajería multiplataforma que le permite enviar mensajes de manera confiable y sin costo.
  * [Analytics](https://firebase.google.com/docs/analytics) Google Analytics es una solución de medición de apps, disponible sin cargo, que proporciona estadísticas sobre el uso de las apps y la participación de los usuarios. 
  * [Crashlytics](https://firebase.google.com/docs/crashlytics) Firebase Crashlytics es una herramienta liviana para informar fallas en tiempo real que te ayuda a hacer un seguimiento de los problemas de estabilidad que afectan la calidad de tu app, a priorizarlos y a corregirlos. 
* [Android Jetpack](https://developer.android.com/jetpack)
   * [ViewModel](https://developer.android.com/topic/libraries/architecture/viewmodel) Se diseñó la clase ViewModel a fin de almacenar y administrar datos relacionados con la IU de manera optimizada para los ciclos de vida. La clase ViewModel permite que se conserven los datos luego de cambios de configuración, como las rotaciones de pantallas.
   * [DataBinding](https://developer.android.com/topic/libraries/data-binding/) La biblioteca de vinculación de datos es una biblioteca de compatibilidad que permite vincular los componentes de la IU de tus diseños a las fuentes de datos de tu app usando un formato declarativo en lugar de la programación.
   * [LiveData](https://developer.android.com/topic/libraries/architecture/livedata) LiveData está optimizado para ciclos de vida, lo que significa que respeta el ciclo de vida de otros componentes de las apps, como actividades, fragmentos o servicios.
* [StateFlow](https://developer.android.com/kotlin/flow/stateflow-and-sharedflow) es un flujo observable contenedor de estados que emite actualizaciones de estado actuales y nuevas a sus recopiladores.
* [Room](https://developer.android.com/jetpack/androidx/releases/room) La biblioteca de persistencias de Room brinda una capa de abstracción para SQLite que permite acceder a la base de datos sin problemas y, al mismo tiempo, aprovechar toda la potencia de SQLite.
* [Glide](https://github.com/bumptech/glide) is an image loading and caching library for Android.
* [Dagger Hilt](https://developer.android.com/training/dependency-injection/hilt-android) Hilt es una biblioteca de inserción de dependencias para Android que permite reducir el trabajo repetitivo de insertar dependencias de forma manual en tu proyecto.
* [Kotlin coroutines](https://developer.android.com/kotlin/coroutines) Código que se ejecuta de forma asíncrona.

## Screenshots
<p float="left">
  <img src="https://firebasestorage.googleapis.com/v0/b/fir-showcase-app.appspot.com/o/screenshots%2Ffirebase-showcase-app%2F01.png?alt=media&token=e34ee02a-f43d-4754-86c1-7fad43a10209" width="250" />
  <img src="https://firebasestorage.googleapis.com/v0/b/fir-showcase-app.appspot.com/o/screenshots%2Ffirebase-showcase-app%2F02.png?alt=media&token=32248ae6-34b5-4841-aa82-6e08c43b1983" width="250" />
  <img src="https://firebasestorage.googleapis.com/v0/b/fir-showcase-app.appspot.com/o/screenshots%2Ffirebase-showcase-app%2F03.png?alt=media&token=98548c2e-551b-4c52-9270-b09960e8c2d7" width="250" />
  <img src="https://firebasestorage.googleapis.com/v0/b/fir-showcase-app.appspot.com/o/screenshots%2Ffirebase-showcase-app%2F04.png?alt=media&token=991f9467-4d2a-405f-bb77-9e1c2f89f21e" width="250" />
  <img src="https://firebasestorage.googleapis.com/v0/b/fir-showcase-app.appspot.com/o/screenshots%2Ffirebase-showcase-app%2F05.png?alt=media&token=da78a07e-a7be-4d60-8d67-49a92789ee90" width="250" />
</p>

## Licence
    MIT License

    Copyright (c) 2023 Armando Rochin

    Permission is hereby granted, free of charge, to any person obtaining a copy
    of this software and associated documentation files (the "Software"), to deal
    in the Software without restriction, including without limitation the rights
    to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
    copies of the Software, and to permit persons to whom the Software is
    furnished to do so, subject to the following conditions:

    The above copyright notice and this permission notice shall be included in all
    copies or substantial portions of the Software.

    THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
    IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
    FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
    AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
    LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
    OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
    SOFTWARE.
