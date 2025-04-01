# gparams

## Introduction

This plugin allow any Pipeline Jobs store and read values common for all jobs. It adds 2 steps in pipeline:
- gpRead(name, value)
- gpWrite(name)

## Getting started

Use of the steps is very simple. For write value in global store add follow step:<br>
`gpWrite name: 'myvalue', value: 'Some string'`

For read this value add follow:<br>
`def myval = gpRead name: 'myvalue'`

## LICENSE

Licensed under MIT, see [LICENSE](LICENSE.md)

