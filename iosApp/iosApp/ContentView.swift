import SwiftUI
import shared;

struct ContentView: View {
    @ObservedObject var noteIOSVM = NoteIOSViewModel()
    
    init() {
        noteIOSVM.observeState()
    }
 
	var body: some View {
        VStack(alignment: HorizontalAlignment.leading) {
            ScrollView {
                ForEach(noteIOSVM.notes, id: \.id) {note in
                    Text(note.text)
                }
            }
            .rotationEffect(Angle(degrees: 180.0)) // rotate the whole ScrollView 180º
            
            HStack {
                TextEditor(text: Binding(get: {noteIOSVM.textFieldValue}, set: {value in noteIOSVM.onTextFielValueChange(text: value)}))
                
                    .frame(maxWidth: .infinity, maxHeight: 30)
                    .background(Color.gray)
                    .padding()
                    
                Button(action: {noteIOSVM.onAddNote()}) {
                    Text("Add")
                }
            }
        }.padding()
	}
}
