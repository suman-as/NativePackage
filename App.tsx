import React, { useEffect, useState } from 'react'
import { SafeAreaView, NativeModules } from 'react-native'
import MyView from './src/MyView'
import BackgroundTimer from 'react-native-background-timer'

function App(): JSX.Element {
  const { BackgroundNotificationService } = NativeModules
  const [count, setCount] = useState<number>(0)
  useEffect(() => {
    // setTimeout(() => {
    //   ProgressBarNotification.showProgressNotification(
    //     20,
    //     'Test Done',
    //     'Test is done',
    //     100,
    //     60,
    //     false
    //   )
    // }, 5000)
    const timer = BackgroundTimer.setInterval(() => {
      setCount(count + 1)
    }, 1000)
    BackgroundNotificationService.startBackgroundService({
      max: '100',
      current: String(count),
    })
    return () => BackgroundTimer.clearInterval(timer)
  }, [count])

  return (
    <SafeAreaView>
      <MyView />
    </SafeAreaView>
  )
}

export default App
