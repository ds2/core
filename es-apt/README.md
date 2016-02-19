# DS/2 OSS Core Elasticsearch Annotation Processor Tool

This artifact here contains the annotation processor to generate some mapping data based on data transfer
objects. The idea is that you generate some mapping information first, then have the Elasticsearch
implementation read the mapping to address indexing issues.

Usually, this artifact should be in scope provided as it does not need to be available during runtime.
