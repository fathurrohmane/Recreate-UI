# SwipeToUnlock
Swipe the button from start to end to perform something. Usually act similar as confirm button.
# Screenshot
![SwipeToUnlock](https://github.com/fathurrohmane/Recreate-UI/blob/main/github/swipetounlock.gif)

# Example usage
```kotlin
MaterialTheme {
    Box(modifier = Modifier.fillMaxSize()) {
        val context = LocalContext.current
        SwipeToUnlockButton(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(16.dp, 32.dp),
            content = {
                Text(text = "Swipe to unlock")
            }
        ) {
            Toast.makeText(context, "Swiped", Toast.LENGTH_SHORT).show()
        }
    }
}
```
