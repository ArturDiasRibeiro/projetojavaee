/* global angular */

angular.module("JavaEEApp", [])
        .value("url", {
            baseUrl: ("http://localhost:8080/javaeeprojeto/rest/")
        })
        .controller("ChamadoController", function ($http, url, $scope) {
            $scope.usuario = "Artur";
            $scope.chamados = [];
            $scope.chamado = undefined;


            $scope.salvar = function () {
                if ($scope.chamado.id) {
                    $http.put(url.baseUrl + "chamados/", $scope.chamado).then(function (response) {
                        $scope.atualizaTabela();
                    }), function (error) {
                        $scope.erro();
                    };
                } else {
                    $http.post(url.baseUrl + "chamados/", $scope.chamado).then(function (response) {
                        $scope.atualizaTabela();
                    }), function (error) {
                        $scope.erro();
                    };
                }
                ;
            };

            $scope.novo = function () {
                $scope.chamado = {};
            };

            $scope.alterar = function (chamado) {
                console.log(chamado);
                $scope.chamado = chamado;
            };

            $scope.deletar = function (chamado) {
                $http.delete(url.baseUrl + "chamados/" + chamado.id).then(function (response) {
                console.log(chamado);
                    $scope.atualizaTabela();
                }, function (error) {
                    $scope.erro();
                });
            };

            $scope.concluir = function (chamado) {
                $http.put(url.baseUrl + "chamados/" + chamado.id + "/").then(function (response) {
                    console.log(chamado);
                    $scope.atualizaTabela();
                }, function (error) {
                    $scope.erro();
                });
            };

            $scope.erro = function () {
                alert("erro");
            };

            $scope.atualizaTabela = function () {
                $http.get(url.baseUrl + "chamados/").then(function (response) {
                    $scope.chamados = response.data;
                    $scope.chamado = undefined;
                }, function (error) {
                    $scope.erro();
                });
            };

            $scope.activate = function () {
                $scope.atualizaTabela();
            };
            $scope.activate();
        });
