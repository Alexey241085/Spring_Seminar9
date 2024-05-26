package ru.gb.Seminar06.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.gb.Seminar06.model.Note;
import ru.gb.Seminar06.repository.NoteRepository;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
//@RequestMapping("/api")
public class NoteController {

    private final NoteRepository noteRepository;

    @GetMapping("/notes")
    public ResponseEntity<List<Note>> getAllNotes() {
        return new ResponseEntity<>(noteRepository.findAll(), HttpStatus.OK);
    }

    @PostMapping("/notes")
    public ResponseEntity<Note> creatNote(@RequestBody Note note) {
        return new ResponseEntity<>(noteRepository.save(note), HttpStatus.CREATED);
    }

    @GetMapping("/notes/{id}")
    public ResponseEntity<Note> getNoteById(@PathVariable Long id) {
        Optional<Note> optionalNote = noteRepository.findById(id);
        if (optionalNote.isPresent()) {
            return new ResponseEntity<>(optionalNote.get(), HttpStatus.FOUND);
        } else
            return ResponseEntity.notFound().build();
    }

    @PutMapping("/notes/{id}")
    public ResponseEntity<Note> updateNote(@PathVariable Long id, @RequestBody Note note) {
        Optional<Note> optionalNote = noteRepository.findById(id);
        try {
            Note findByIdNote = optionalNote.get();
            findByIdNote.setTitle(note.getTitle());
            findByIdNote.setContent(note.getContent());
            return new ResponseEntity<>(noteRepository.save(findByIdNote), HttpStatus.OK);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Note());
        }
    }

    @DeleteMapping("/notes/{id}")
    public ResponseEntity<Note> deleteNoteById(@PathVariable Long id) {
        Optional<Note> optionalNote = noteRepository.findById(id);
        if (optionalNote.isPresent()) {
            ;
            noteRepository.delete(optionalNote.get());
            return ResponseEntity.status(HttpStatus.OK).build();
        } else
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
}
