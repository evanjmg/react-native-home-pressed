
# react-native-home-pressed

## Getting started

`$ npm install react-native-home-pressed --save`

### Automatic installation (no further setup required)

`$ react-native link react-native-home-pressed`


### Manual installation


#### Android

1. Open up `android/app/src/main/java/[...]/MainActivity.java`
  - Add `import com.evanjmg.RNHomePressedPackage;` to the imports at the top of the file
  - Add `new RNHomePressedPackage()` to the list returned by the `getPackages()` method
2. Append the following lines to `android/settings.gradle`:
    ```
    include ':react-native-home-pressed'
    project(':react-native-home-pressed').projectDir = new File(rootProject.projectDir,   '../node_modules/react-native-home-pressed/android')
    ```
3. Insert the following lines inside the dependencies block in `android/app/build.gradle`:
    ```
      compile project(':react-native-home-pressed')
    ```
## Events
- 'ON_HOME_BUTTON_PRESSED'
- 'ON_RECENT_APP_BUTTON_PRESSED' - that button that lists your apps

## Usage
```javascript
import { DeviceEventEmitter } from 'react-native'

class ExampleComponent extends Component {
  ...
  componentDidMount() {
    DeviceEventEmitter.addListener(
     'ON_HOME_BUTTON_PRESSED',
     () => {
       console.log('You tapped the home button!')
    })
  }
  ...
}

```

## TODOS
 - Export event names and listener
 - Support Other Buttons - e.g navigation button

## Credit
Jack's Stackoverflow post - [https://stackoverflow.com/questions/8881951/detect-home-button-press-in-android]
