import React, { useEffect } from 'react'
import { SafeAreaView, NativeModules } from 'react-native'
import MyView from './src/MyView'

function App(): JSX.Element {
  const { ProgressBarNotification } = NativeModules
  useEffect(() => {
    ProgressBarNotification.showProgressNotification(
      20,
      'Test in progress',
      'Test in progress',
      100,
      60
    )
  }, [])

  return (
    <SafeAreaView>
      <MyView />
    </SafeAreaView>
  )
}

export default App
