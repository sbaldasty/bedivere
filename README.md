This project is not ready for use yet.

# Issue tracker
It's just me developing right now. Here are things I've done and plan to do.

### Aesthetics
- [ ] Height of claim citations table should vary with contents
- [ ] What is wrong with table column widths?
- [ ] Claim detail form should have section headers
- [ ] Claim supports should stack vertically

### Bugs
- [ ] Fix concurrent change listener list modifications

### Functionality
- [x] Split pane between source list and tabbed editors
- [x] Make left pane horizontally scrollable
- [x] Add claim list and editor
- [x] Set source for citations
- [ ] Proper label in sources list
- [ ] Source label in claim citations list
- [ ] Make tabbed editors closable
- [ ] Titles for tabbed editors
- [ ] Should be possible to remove citations from claims
- [ ] Editor tab focus should switch on opening something new
- [ ] No duplicate editor tabs

### Technical enhancements
- [x] Improvise a double-click listener for lists
- [ ] Make cell renderer abstraction from `CustomRenderer`
- [ ] Abstract the idea of a `Binder` for better reuse
- [ ] Use swing editor for main form
- [ ] Fire change events from table cell edits
- [ ] Add kotlin linter

### Cleanup
- Only allow citation source settings when a citation is selected