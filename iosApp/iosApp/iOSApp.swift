import SwiftUI
import shared

@main
struct iOSApp: App {
    
    init() {
        HelpersKt.doInitKoin()
    }
    
	var body: some Scene {
		WindowGroup {
			ContentView()
		}
	}
}
