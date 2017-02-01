[![BCH compliance](https://bettercodehub.com/edge/badge/ishepard/MockExtractor)](https://bettercodehub.com)
[![Build Status](https://travis-ci.com/ishepard/MockExtractor.svg?token=J3YWhdMEr4RvUk6qZbMK&branch=master)](https://travis-ci.com/ishepard/MockExtractor)

MockExtractor extracts the list of mocked and non mocked dependencies in
all test units (JUnit tests) of a given system.

This tool was part of our MSR 2017 paper _(to be accepted)_.

# Usage

Usage: `java -jar <tool>.jar <directory> <dir-deps> <output.csv>`

_directory_: directory of the app to be analyzed.
_dir-deps_: directory of the JAR dependencies of that file.
_output.csv_: path to the final CSV file

Check `Runner.java`.


# License

This software is licensed under the Apache 2.0 License.
