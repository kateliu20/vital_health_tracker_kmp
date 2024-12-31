import SwiftUI
import shared

struct ContentView: View {
    private let component: HealthComponent

    init() {
        self.component = HealthComponent.Companion().create()
    }

    var body: some View {
        ComposeView(component: component)
            .ignoresSafeArea(.all, edges: .all)
    }
}

struct ComposeView: UIViewControllerRepresentable {
    private let component: HealthComponent

    init(component: HealthComponent) {
        self.component = component
    }

    func makeUIViewController(context: Context) -> UIViewController {
        return IosUIKt.makeUIViewController(component: component)
    }

    func updateUIViewController(_ uiViewController: UIViewController, context: Context) {}
}

struct ContentView_Previews: PreviewProvider {
    static var previews: some View {
        ContentView()
    }
}