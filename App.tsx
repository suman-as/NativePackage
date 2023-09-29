import React, { useEffect, useState } from 'react'
import { SafeAreaView, NativeModules } from 'react-native'
import MyView from './src/MyView'
import { RNNotification } from './packages/RNNotification'

function App(): JSX.Element {
  const [count, setCount] = useState<number>(0)

  useEffect(() => {
    RNNotification.showNotification({
      channelID: '123',
      notificationID: 55,
      title: 'Test',
      content: 'Tested',
    })
    RNNotification.showNotification({
      channelID: '123',
      notificationID: 505,
      title: 'Test',
      content: 'Tested',
      isProgress: true,
      indeterminate: true,
      onGoing: true,
    })
    setTimeout(() => {
      RNNotification.cancelAllNotification()
    }, 5000)
  }, [])

  return (
    <SafeAreaView>
      <MyView />
    </SafeAreaView>
  )
}

export default App
