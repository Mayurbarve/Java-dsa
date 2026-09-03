Designing the Cloud Storage SDK
The cloud-storage exercise tied the patterns together into one realistic design. The goal was to support multiple cloud providers such as AWS, GCP, and Azure while keeping the app modular.
The system needed:
a shared config manager
a complex storage request
provider-specific storage clients
provider-specific loggers
The central idea was to create a family of related objects for each provider so the app never mixes incompatible combinations.
Key insight: One provider choice should determine multiple compatible products together.
A good mental model is:
Config manager: stores the provider and shared settings
Storage request: describes the upload/download action
Storage client interface: defines upload and download
Concrete clients: AWS, GCP, Azure implementations
Logger interface / implementations: provider-specific logging behavior
Abstract factory: creates the matching client and logger pair for a given provider
For example:
AWS → AWS storage client + AWS logger
GCP → GCP storage client + GCP logger
Azure → Azure storage client + Azure logger
That makes the app consistent and prevents mixing families.
Watch out: The cleaner architecture is to choose the factory based on config and then inject that factory into the cloud app, rather than scattering provider selection throughout the code.
Key takeaway: The cloud SDK is a pattern-composition problem: singleton for config, builder for request objects, and factory/abstract factory for provider-specific families.
How to Approach Design Problems Like This
A major theme of the session was the right way to reason about these problems. Don’t start by forcing patterns onto the code. Start from the required behavior and work backward.
The recommended approach was:
Identify the operations the client needs
e.g. upload, download, logging methods
Define the interface contracts
storage client interface
logger interface
Introduce request objects where needed
especially when the object has many fields
Map out the implementation families
AWS, GCP, Azure versions
Use abstract factory when a single choice determines a matching set of products
Build from the outside in: first the responsibilities, then the contracts, then the implementations.
This also helps with interview speed. You do not want to spend too long inventing class structure from scratch during a timed problem. Recognizing the right pattern quickly lets you move directly into implementation.
Watch out: It is easy to get stuck trying to connect storage client and storage request too early. First identify what each class is responsible for, then decide how they fit.
Key takeaway: Start from responsibilities and use cases; patterns should emerge from the structure of the problem.
