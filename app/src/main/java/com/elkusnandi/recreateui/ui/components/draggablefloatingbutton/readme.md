# DraggableFloatingButton
This is inspired by e-commerce applications where its has button like Floating Action Button that is draggable and dismissable. This button is always snap to the edge of the screen to avoid obscuring the main UI.

# Screenshot
![Shopee UI](https://github.com/fathurrohmane/Recreate-UI/blob/main/github/shoppeui.gif)
![DraggableFloatingButton](https://github.com/fathurrohmane/Recreate-UI/blob/main/github/draggablefloatingbutton.gif)

# Example usage
```kotlin
Box(modifier = Modifier.fillMaxSize()) {
    Button(onClick = {}) {
        Text(text = "Back button")
    }
    DraggableFloatingButton(
        modifier = Modifier.fillMaxSize(),
    ) {
        FloatingActionButton(
            onClick = { },
            modifier = it.padding(16.dp)
        ) {
            Image(imageVector = Icons.Default.Add, contentDescription = "")
        }
    }
}
```
