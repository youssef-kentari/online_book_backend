resource "null_resource" "create_kind_cluster" {
  provisioner "local-exec" {
    command     = "./create_kind_cluster.sh"
    interpreter = ["/bin/bash", "-c"]
  }

  triggers = {
    config_sha = filesha256("../create_cluster.yml")
  }
}

resource "null_resource" "install_argocd" {
  provisioner "local-exec" {
    command     = "./setup_argoCD.sh"
    interpreter = ["/bin/bash", "-c"]
  }

  depends_on = [null_resource.create_kind_cluster]
}
