import { NativeModules } from 'react-native'

// Define a type for the parameters used in the showNotification method
export interface ShowNotificationParams {
  channelID: string
  channelName?: string
  notificationID: number
  title: string
  content: string
  onGoing?: boolean
  isProgress?: boolean
  priority?: number
  max?: number
  current?: number
  indeterminate?: boolean
}

// Define a type for the parameters used in the cancelNotification method
export interface CancelNotificationParams {
  notificationID: number
}

const NotificationModule = NativeModules.RNNotification

export const RNNotification = {
  showNotification: (params: ShowNotificationParams) => {
    NotificationModule.showNotification(params)
  },
  cancelNotification: (params: CancelNotificationParams) => {
    NotificationModule.cancelNotification(params)
  },
  cancelAllNotification: () => {
    NotificationModule.cancelAllNotification()
  },
}
