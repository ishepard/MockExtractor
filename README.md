[![BCH compliance](https://bettercodehub.com/edge/badge/ishepard/MockExtractor)](https://bettercodehub.com)
[![Build Status](https://travis-ci.com/ishepard/MockExtractor.svg?token=J3YWhdMEr4RvUk6qZbMK&branch=master)](https://travis-ci.com/ishepard/MockExtractor)

MockExtractor extracts the list of mocked and non mocked dependencies in
all test units (JUnit tests) of a given system.

This tool was part of our [MSR 2017 paper](https://pure.tudelft.nl/portal/en/publications/to-mock-or-not-to-mock(a0b02521-ad00-4f96-9d96-17a85dc23f99).html).

# Build

	mvn clean package assembly:single
	

# Usage

Usage: `java -jar <tool>.jar <directory> <dir-deps> <output.csv>`

- _directory_: directory of the app to be analyzed.
- _dir-deps_: directory of the JAR dependencies of that file.
- _output.csv_: path to the final CSV file.

# Example

	java -jar target/tool-mockextractor-0.0.1-SNAPSHOT-jar-with-dependencies.jar tests tests/deps/ output.csv

# License

This software is licensed under the Apache 2.0 License.
