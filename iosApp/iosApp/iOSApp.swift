import SwiftUI
import ComposeApp

@main
struct iOSApp: App {

    init() {
        MainViewControllerKt.InitModule()
    }

    var body: some Scene {
        WindowGroup {
            ContentView()
        }
    }
}
