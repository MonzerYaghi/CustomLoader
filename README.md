# CustomLoader
CustomLoader is an overlay loader that is displayed on the entire screen, it can aslo support lottie animation

## Installation

To start using this library, follow the steps below :

- Add the following to your root build.gradle at the end of repositories:

```bash
allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
```

- Add this dependency to your app level build.gradle file

```bash
dependencies {
    ...
    implementation 'com.github.MonzerYaghi:CustomLoader:1.0.0'
}
```

## Layout Description

Technically, the overlay loader is a transparent full screen dialog. This dialog has an xml layout, the layout has 4 components:

- AppCompatImageView, which we can use to set an array of images to be animated
- LottieAnimationView, which we can use to set an json file to provide animation
- ProgressBar, which we can use to display the native progress bar if we want
- AppCompatTextView, which we can use to set a loading message

## Usage

The typical usage will be as follows:

```bash
val customLoader = CustomLoader(context)
customLoader.showLoader()
```

if you want to show a loading message, simply use
```bash
customLoader.showLoader("Loading...")
```

The loader is by default uncancelable, knowing that we are creating a dialog, if you want to be able to cancel the loader simnply add the following:
```bash
customLoader.setCancelable(true)
```

## Important Notice

By default, the loader shows a native progress bar loading. Inside the CustomLoader class, the following variable are static:

```bash
var lottieAnimationFileName = ""
var indeterminateDrawable: Drawable? = null
var animatedDrawableResource = -1
```

These variable are made static so that the user can change the loader style only one time in the application (for example in the App class), so:

* If the user wants to use a lottie animation file:

1- He should put the .json file in the assets folder

2- Write the following code before initializing the loader:

 ```bash
CustomLoader.lottieAnimationFileName = "loader.json"
```

* If the user wants to use an animated drawable (array of images):
 
 ```bash
CustomLoader.animatedDrawableResource = R.drawable.loader_animation
```

* If the user wants to use an animated drawable (array of images):
 
 ```bash
CustomLoader.indeterminateDrawable = R.drawable.progress_drawable
```

