import React, {useEffect} from 'react';
import {SafeAreaView, NativeModules} from 'react-native';

function App(): JSX.Element {
  const {ProgressBarNotification} = NativeModules;
  useEffect(() => {
    ProgressBarNotification.add(5, 20, (res: number) => console.log(res));
  }, [ProgressBarNotification]);

  return <SafeAreaView></SafeAreaView>;
}

export default App;
