This project is not ready for use yet.

# Issue tracker
It's just me developing right now. Here are things I've done and plan to do.

### Aesthetics
- [x] Claim detail form should have section headers
- [x] Height of claim citations table should vary with contents
- [x] Claim detail should not fill vertical space unless it needs to
- [x] Claim supports should stack vertically
- [ ] What is wrong with table column widths?

### Bugs
- [x] Fix concurrent change listener list modifications
- [ ] Citations and sources should not have empty titles
- [ ] Only allow citation source settings when a citation is selected
- [ ] Text table cells do not lose focus in time for save

### Functionality
- [x] Split pane between source list and tabbed editors
- [x] Make left pane horizontally scrollable
- [x] Add claim list and editor
- [x] Set source for citations
- [x] Proper label in sources list
- [x] Source label in claim citations list
- [x] Titles for tabbed editors
- [x] Should be possible to remove citations from claims
- [x] Editor tab focus should switch on opening something new
- [x] No duplicate editor tabs
- [ ] Add the support claims table
- [ ] Make tabbed editors closable

### Technical enhancements
- [x] Improvise a double-click listener for lists
- [x] Make cell renderer abstraction from `CustomRenderer`
- [x] Abstract the idea of a `Binder` for better reuse
- [x] Use swing editor for main form
- [x] Forms belong in a Java source root
- [x] Add kotlin linter
- [ ] Move table binders into bind package
- [ ] Fire change events from table cell edits
- [ ] Clean up warnings from the Form GUI
