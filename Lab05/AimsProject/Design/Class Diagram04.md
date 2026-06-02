classDiagram
    %% --- INTERFACES & COMPARATORS ---
    class Playable {
        <<interface>>
        +play() void
    }
    
    class Comparator~T~ {
        <<interface>>
        +compare(o1: T, o2: T) int
    }

    class MediaComparatorByTitleCost {
        +compare(o1: Media, o2: Media) int
    }
    
    class MediaComparatorByCostTitle {
        +compare(o1: Media, o2: Media) int
    }

    %% --- CORE DATA MODELS ---
    class Media {
        <<abstract>>
        -id: int
        -title: String
        -category: String
        -cost: float
        +isMatch(title: String) boolean
        +toString() String*
        +getId() int
        +getTitle() String
        +getCategory() String
        +getCost() float
    }

    class Book {
        -authors: List~String~
        +addAuthor(name: String) void
        +removeAuthor(name: String) void
        +toString() String
    }

    class Disc {
        <<abstract>>
        -length: int
        -director: String
        +getLength() int
        +getDirector() String
    }

    class CompactDisc {
        -artist: String
        -tracks: List~Track~
        +addTrack(track: Track) void
        +removeTrack(track: Track) void
        +getLength() int
        +play() void
        +toString() String
        +getArtist() String
    }

    class DigitalVideoDisc {
        +play() void
        +toString() String
    }

    class Track {
        -title: String
        -length: int
        +play() void
        +getTitle() String
        +getLength() int
    }

    %% --- BUSINESS LOGIC CLASSES ---
    class Cart {
        -itemsOrdered: ObservableList~Media~
        +addMedia(media: Media) void
        +removeMedia(media: Media) void
        +totalCost() float
        +sortByTitleCost() void
        +sortByCostTitle() void
        +getItemsOrdered() ObservableList~Media~
    }

    class Store {
        -itemsInStore: ArrayList~Media~
        +addMedia(media: Media) void
        +removeMedia(media: Media) void
        +printStore() void
        +getItemsInStore() ArrayList~Media~
    }

    %% --- LAB 05 GUI CLASSES ---
    class StoreScreen {
        -store: Store
        -cart: Cart
        +createNorth() JPanel
        +createCenter() JPanel
    }

    class MediaStore {
        -media: Media
        -cart: Cart
        +MediaStore(media: Media, cart: Cart)
    }

    class CartScreen {
        -cart: Cart
    }

    class CartScreenController {
        -cart: Cart
        -tblMedia: TableView~Media~
        -totalCost: Label
        +setCart(cart: Cart) void
        -updateTotalCost() void
    }

    %% --- RELATIONSHIPS & INHERITANCE ---
    Media <|-- Book
    Media <|-- Disc
    Disc <|-- DigitalVideoDisc
    Disc <|-- CompactDisc
    
    Playable <|.. DigitalVideoDisc
    Playable <|.. CompactDisc
    Playable <|.. Track
    
    Comparator <|.. MediaComparatorByTitleCost
    Harris_Media <.. MediaComparatorByTitleCost : compares
    Comparator <|.. MediaComparatorByCostTitle
    Harris_Media <.. MediaComparatorByCostTitle : compares

    CompactDisc "1" *-- "many" Track : contains
    Store "1" o-- "many" Media : manages
    Cart "1" o-- "many" Media : orders

    %% GUI Connections
    StoreScreen "1" --> "1" Store : displays
    StoreScreen "1" --> "1" Cart : shares
    StoreScreen "1" *-- "many" MediaStore : contains
    MediaStore "1" --> "1" Media : displays info
    MediaStore "1" --> "1" Cart : adds item to
    
    CartScreen "1" --> "1" Cart : displays
    CartScreen "1" --> "1" CartScreenController : controls
    CartScreenController "1" --> "1" Cart : updates & listens