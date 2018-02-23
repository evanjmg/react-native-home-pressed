
# react-native-home-pressed

## Getting started

`$ npm install react-native-home-pressed --save`

### Mostly automatic installation

`$ react-native link react-native-home-pressed`

### Manual installation


#### Android

1. Open up `android/app/src/main/java/[...]/MainActivity.java`
  - Add `import com.reactlibrary.RNHomePressedPackage;` to the imports at the top of the file
  - Add `new RNHomePressedPackage()` to the list returned by the `getPackages()` method
2. Append the following lines to `android/settings.gradle`:
  	```
  	include ':react-native-home-pressed'
  	project(':react-native-home-pressed').projectDir = new File(rootProject.projectDir, 	'../node_modules/react-native-home-pressed/android')
  	```
3. Insert the following lines inside the dependencies block in `android/app/build.gradle`:
  	```
      compile project(':react-native-home-pressed')
  	```


## Usage
```javascript
import RNHomePressed from 'react-native-home-pressed';

// TODO: What to do with the module?
RNHomePressed;
```
  