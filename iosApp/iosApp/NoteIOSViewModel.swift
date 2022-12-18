import Foundation
import shared

class NoteIOSViewModel: ObservableObject {
    let noteViewmodel = ViewModels().noteViewModel()
    
    @Published var notes = [Note]()
    @Published var textFieldValue = ""
    
    func onTextFielValueChange(text: String) {
        noteViewmodel.onValueChange(text: text)
    }
    
    func onAddNote() {
        noteViewmodel.addNote()
    }
    
    func onDeleteNote(id: String) {
        noteViewmodel.onDelete(id: id)
    }

    func observeState() {
        noteViewmodel.notes.subscribe(onCollect: { list in
    //            if let list = list {
                self.notes = list as! [Note]
    //            }
        })
        
        noteViewmodel.textFieldValue.subscribe { text in
            self.textFieldValue = text as String? ?? ""
        }
    }
}
