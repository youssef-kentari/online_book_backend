#!/bin/bash
set -e

if ! kind get clusters | grep -q "^my-cluster$"; then
  echo "Creating Kind cluster..."
  kind create cluster --name my-cluster --config=../create_cluster.yml
else
  echo "Kind cluster already exists. Skipping creation."
fi
