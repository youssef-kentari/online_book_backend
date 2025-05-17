#!/bin/bash
kubectl create namespace argocd || echo "Namespace argocd already exists"
kubectl apply -n argocd -f https://raw.githubusercontent.com/argoproj/argo-cd/stable/manifests/install.yaml
