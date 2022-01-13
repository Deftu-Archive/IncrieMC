<div align="center">

# `Incrie`
Core utilities and APIs for **Minecraft modding**.\
[Report a bug][bugreps]
·
[Request a feature][featreq]
·
[Wiki][wiki]
·
[Documentation][docs]

</div>

## Summary
- [Examples][basexams]

## Examples
<details>
    <summary>Examples</summary>

### Notifications
#### Java
```java
package com.example;

import net.fabricmc.api.ClientModInitializer;
import xyz.incrie.api.Incrie;
import xyz.incrie.api.gui.notifications.NotificationAlignment;

public class ExampleMod implements ClientModInitializer {
    public void onInitializeClient() {
        Incrie.enqueuePostInitializationOperation(() -> {
            Incrie.getNotifications().post(
                    "Title",
                    "Description",
                    NotificationAlignment.BOTTOM_RIGHT,
                    IncrieTheme.DEFAULT,
                    notification -> {
                        System.out.println("The example notification was clicked! (" + notification + ")");
                    }
            );
        });
    }
}
```
#### Kotlin
```kt
package com.example

import net.fabricmc.api.ClientModInitializer
import xyz.incrie.api.Incrie
import xyz.incrie.api.gui.notifications.NotificationAlignment

class ExampleMod : ClientModInitializer {
    override fun onInitializeClient() {
        Incrie.enqueuePostInitializationOperation {
            Incrie.getNotifications().post(
                "Title",
                "Description",
                NotificationAlignment.BOTTOM_RIGHT,
                IncrieTheme.DEFAULT
            ) {
                println("The example notification was clicked! ($it)")
            }
        }
    }
}
```
</details>

[basexams]: #Examples

[bugreps]: https://github.com/Incrie/Incrie/issues
[featreq]: https://github.com/Incrie/Incrie/issues
[wiki]: https://wiki.incrie.xyz/
[docs]: https://docs.incrie.xyz/